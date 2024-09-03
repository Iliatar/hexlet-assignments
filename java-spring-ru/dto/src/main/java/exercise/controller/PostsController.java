package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {
    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;
    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public List<PostDTO> index() {
        List<Post> posts = postRepository.findAll();
        List<PostDTO> postDTOList = new ArrayList<>(posts.size());
        for (Post post : posts) {
            postDTOList.add(postDTOFromModel(post));
        }

        return postDTOList;
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDTO show(@PathVariable Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
        return postDTOFromModel(post);
    }

    private PostDTO postDTOFromModel(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setBody(post.getBody());
        postDTO.setTitle(post.getTitle());

        List<CommentDTO> commentDTOList = new ArrayList<>();
        List<Comment> comments = commentRepository.findByPostId(post.getId());

        for (Comment comment : comments) {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setBody(comment.getBody());
            commentDTO.setId(comment.getId());
            commentDTOList.add(commentDTO);
        }

        postDTO.setComments(commentDTOList);
        return postDTO;
    }
}
// END
