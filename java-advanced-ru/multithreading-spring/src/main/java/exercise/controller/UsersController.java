package exercise.controller;

import exercise.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import exercise.service.UserService;


@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "")
    public Flux<User> getUsers() {
        return userService.findAll();
    }

    // BEGIN
    @GetMapping(path = "/{id}")
    public Mono<User> getUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping(path = "")
    public Mono<User> create(@RequestBody User user) {
        return userService.create(user);
    }

    @PatchMapping(path = "/{id}")
    public Mono<User> update(@RequestBody User user, @PathVariable Long id) {
        return userService.update(id, user);
    }

    @DeleteMapping(path = "/{id}")
    public Mono delete(@PathVariable Long id) {
        return userService.deleteById(id);
    }
    // END
}
