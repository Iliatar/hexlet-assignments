package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.util.ArrayList;
import java.util.List;

public class PostsController {
    private static int PAGE_SIZE = 5;

    // BEGIN
    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Page not found"));
        var page = new PostPage(post);
        ctx.render("posts/show.jte", model("page", page));
    }

    public static void index(Context ctx) {
        var pageNum = ctx.queryParamAsClass("page", Integer.class)
                .getOrDefault(1);
        var posts = PostRepository.findAll(pageNum, PAGE_SIZE);
        List<PostPage> postPages = new ArrayList<>();
        for (Post post : posts) {
            postPages.add(new PostPage(post));
        }
        PostsPage postsPage = new PostsPage(postPages, pageNum);
        ctx.render("posts/index.jte", model("postsPage", postsPage));
    }
    // END
}
