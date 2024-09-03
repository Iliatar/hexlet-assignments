package exercise.controller;

import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    // BEGIN
    @Test
    public void testShow() throws Exception {
        Task data = getFakeTask();
        taskRepository.save(data);
        var id = data.getId();
        var title = data.getTitle();
        var desc = data.getDescription();

        var result = mockMvc.perform(get("/tasks/" + id))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();

        assertThatJson(body).and(
                a -> a.node("title").isEqualTo(title),
                a -> a.node("description").isEqualTo(desc)
        );
    }

    @Test
    public void testCreate() throws Exception {
        Task data = getFakeTask();
        taskRepository.save(data);

        var request = post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(data));

        mockMvc.perform(request)
                .andExpect(status().isCreated());

        Task task = taskRepository.findById(data.getId()).get();
        assertThat(task.getDescription()).isEqualTo(data.getDescription());
    }

    @Test
    public void testUpdate() throws Exception {
        final String updatedTitle = "updatedTitle";

        Task data = getFakeTask();
        taskRepository.save(data);

        HashMap<String, String> updateData = new HashMap<>();
        updateData.put("title", updatedTitle);

        var request = put("/tasks/" + data.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(updateData));

        mockMvc.perform(request)
                .andExpect(status().isOk());

        Task task = taskRepository.findById(data.getId()).get();
        assertThat(task.getTitle()).isEqualTo(updatedTitle);
    }

    @Test
    public void testDelete() throws Exception {
        Task data = getFakeTask();
        taskRepository.save(data);

        var request = delete("/tasks/" + data.getId());

        mockMvc.perform(request)
                .andExpect(status().isOk());

        var maybeTask = taskRepository.findById(data.getId());
        assertThat(maybeTask).isNotPresent();
    }

    private Task getFakeTask() {
        var task = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .ignore(Select.field(Task::getCreatedAt))
                .ignore(Select.field(Task::getUpdatedAt))
                .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                .supply(Select.field(Task::getDescription), () -> faker.lorem().paragraph())
                .create();
        return task;
    }
    // END
}
