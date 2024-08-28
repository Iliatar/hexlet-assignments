package exercise;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import  org.springframework.beans.factory.annotation.Autowired;

import exercise.model.User;
import exercise.component.UserProperties;

@SpringBootApplication
@RestController
public class Application {

    @Component
    @ConfigurationProperties(prefix = "users")
    @Getter
    @Setter
    static class AdminList {
        List<String> admins;
    }

    // Все пользователи
    private List<User> users = Data.getUsers();

    // BEGIN
    @Autowired
    private AdminList adminList;

    @GetMapping("/admins")
    public List<String> adminIndex() {
        List<String> result = users.stream()
                .filter(u -> adminList.admins.contains(u.getEmail()))
                .map(u -> u.getName())
                .sorted()
                .toList();
        return result;
    }
    // END

    @GetMapping("/users")
    public List<User> index() {
        return users;
    }

    @GetMapping("/users/{id}")
    public Optional<User> show(@PathVariable Long id) {
        var user = users.stream()
            .filter(u -> u.getId() == id)
            .findFirst();
        return user;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
