package exercise;

import io.javalin.Javalin;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import io.javalin.http.NotFoundResponse;

// BEGIN

// END

public final class App {

    private static final List<Map<String, String>> COMPANIES = Data.getCompanies();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        // BEGIN
        
        // END

        app.get("/companies/", ctx -> {
            ctx.json(COMPANIES);
        });

        app.get("/", ctx -> {
            ctx.result("open something like (you can change id): /companies/5");
        });

        app.get("/companies/{id}", ctx -> {
           String id = ctx.pathParam("id");
            System.out.println("id = " + id);
           var optional = getCompanieById(id);
           if (optional.isPresent()) {
               ctx.json(optional.get());
           } else {
               ctx.status(404);
               throw new NotFoundResponse("Company not found");
           }
        });

        return app;
    }

    private static Optional<Map<String, String>> getCompanieById (String id) {
        Optional<Map<String, String>> result = Optional.empty();
        for (Map<String, String> companie : Data.getCompanies()) {
            if (companie.get("id").equals(id)) {
                result = Optional.of(companie);
                break;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
