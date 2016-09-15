package ar.com.osde.esb.component.changelogManager.app.controller;


import ar.com.osde.esb.component.changelogManager.app.utils.JsonTransformer;
import ar.com.osde.esb.component.changelogManager.app.utils.ResponseObject;
import ar.com.osde.esb.component.changelogManager.domain.Component;
import ar.com.osde.esb.component.changelogManager.domain.repository.ComponentRepository;
import com.google.gson.Gson;

import static spark.Spark.*;
import static spark.Spark.post;

public class ComponentController {

    /* Repository Singleton */
    private static ComponentRepository repoComponentes = ComponentRepository.getInstance();

    private JsonTransformer jsonTransformer;
    private Gson gson;

    public ComponentController(Gson gson, JsonTransformer transformer){
        this.jsonTransformer = transformer;
        this.gson = gson;
    }

    public void register(){

        /** Halt server on exception **/

        exception(RuntimeException.class, (ex, request, response) -> {
            ex.printStackTrace();
            response.body(ex.getMessage());
            halt(400);
        });


        /** Get all service changelogs **/

        get("/projects", "application/json;charset=utf-8", (request, response) -> {
            response.type("application/json;charset=utf-8");
            return repoComponentes.all();
        }, this.jsonTransformer);


        /** Get a specific service changelog **/

        get("/components/:name", "application/json;charset=utf-8", (request, response) -> {
            response.type("application/json;charset=utf-8");
            return repoComponentes.get(request.params(":name"));
        }, this.jsonTransformer);


        /** Delete a specific service changelog **/

        delete("/components/:name", "application/json;charset=utf-8", (request, response) -> {
            response.type("application/json;charset=utf-8");
            repoComponentes.delete(repoComponentes.get(request.params(":name")));
            response.status(200);
            return new ResponseObject(200, "success");
        }, this.jsonTransformer);


        /** Create a service changelog **/

        post("/components/:name", "application/json;charset=utf-8", (request, response) -> {
            response.type("application/json;charset=utf-8");
            if(createComponent(request.body())) return new ResponseObject(200, "success");
            return new ResponseObject(500, "failure");
        }, this.jsonTransformer);


    }

    private Boolean createComponent(String body){

        Component component = gson.fromJson(body, Component.class);
        return repoComponentes.add(component);
    }
}
