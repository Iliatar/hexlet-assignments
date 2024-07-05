package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.model.User;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;

import exercise.util.NamedRoutes;
import exercise.util.Security;
import io.javalin.http.Context;
import org.checkerframework.checker.units.qual.N;

public class SessionsController {

    // BEGIN
    public static void build (Context ctx) {
        LoginPage loginPage = new LoginPage(null, null);
        ctx.render("build.jte", model("page", loginPage));
    }

    public static void create (Context ctx) {
        var nickname = ctx.formParam("name");
        var password = ctx.formParam("password");
        var hashedPassword = Security.encrypt(password);

        User user = UsersRepository.findByName(nickname);

        if (user == null || !user.getPassword().equals(hashedPassword)) {
            LoginPage page = new LoginPage(nickname, "Wrong username or password.");
            ctx.render("build.jte", model("page", page));
        } else {
            MainPage page = new MainPage(nickname);
            ctx.sessionAttribute("currentUser", nickname);
            ctx.redirect(NamedRoutes.rootPath());
        }
    }

    public static void delete (Context ctx) {
        ctx.sessionAttribute("currentUser", null);
        ctx.redirect(NamedRoutes.rootPath());
    }
    // END
}
