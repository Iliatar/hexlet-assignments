package exercise.controller;

import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;
import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.repository.UserRepository;
import exercise.dto.users.UserPage;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;

import javax.naming.Name;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    // BEGIN
    public static void show(Context ctx) {
        String userToken = ctx.cookie("securityToken");
        Long id = ctx.pathParamAsClass("id", Long.class).get();
        User user = UserRepository.find(id).get();
        if (user != null && user.getToken().equals(userToken)) {
            UserPage page = new UserPage(user);
            ctx.render("users/show.jte", model("page", page));
        } else {
            ctx.redirect(NamedRoutes.buildUserPath());
        }
    }

    public static void buildPost (Context ctx) {
        var firstName = ctx.formParam("firstName");
        var lastName = ctx.formParam("lastName");
        var email = ctx.formParam("email");
        var pass = ctx.formParam("password");
        var token = Security.generateToken();

        ctx.cookie("securityToken", token);
        User user = new User(firstName, lastName, email, pass, token);
        UserRepository.save(user);
        ctx.redirect("/users/" + user.getId());
    }
    // END
}
