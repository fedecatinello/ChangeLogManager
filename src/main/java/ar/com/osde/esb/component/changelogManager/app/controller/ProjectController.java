package ar.com.osde.esb.component.changelogManager.app.controller;

import ar.com.osde.esb.component.changelogManager.app.utils.JsonTransformer;
import ar.com.osde.esb.component.changelogManager.domain.Component;
import ar.com.osde.esb.component.changelogManager.domain.repository.ComponentRepository;
import ar.com.osde.esb.component.changelogManager.domain.repository.ServiceRepository;
import com.google.gson.Gson;

import java.util.List;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.halt;

public class ProjectController {

    /* Repository Singleton */
    private static ServiceRepository repoServicios = ServiceRepository.getInstance();
    private static ComponentRepository repoComponentes = ComponentRepository.getInstance();

    private JsonTransformer jsonTransformer;
    private Gson gson;

    public ProjectController(Gson gson, JsonTransformer transformer){
        this.jsonTransformer = transformer;
        this.gson = gson;
    }


    public void register() {

        /** Halt server on exception **/

        exception(RuntimeException.class, (ex, request, response) -> {
            ex.printStackTrace();
            response.body(ex.getMessage());
            halt(400);
        });

        /** Get all project changelogs **/

        get("/projects", "application/json;charset=utf-8", (request, response) -> {
            response.type("application/json;charset=utf-8");
            return repoComponentes.all();
        }, this.jsonTransformer);
    }

}
