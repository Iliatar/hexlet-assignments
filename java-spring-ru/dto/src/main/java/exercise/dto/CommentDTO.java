package exercise.dto;

import exercise.model.Comment;
import lombok.Getter;
import lombok.Setter;

// BEGIN
@Getter
@Setter
public class CommentDTO {
    private long id;
    private String body;
}
// END
