package ar.com.osde.esb.component.changelogManager.app.controller;

import ar.com.osde.esb.component.changelogManager.app.utils.JsonTransformer;
import ar.com.osde.esb.component.changelogManager.app.utils.ResponseObject;
import ar.com.osde.esb.component.changelogManager.domain.Service;
import ar.com.osde.esb.component.changelogManager.domain.repository.ServiceRepository;
import com.google.gson.Gson;

import static spark.Spark.*;

public class ServiceController {

    /* Repository Singleton */
    private static ServiceRepository repoServicios = ServiceRepository.getInstance();

    private JsonTransformer jsonTransformer;
    private Gson gson;

    public ServiceController(Gson gson, JsonTransformer transformer){
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

        get("/services", "application/json;charset=utf-8", (request, response) -> {
            response.type("application/json;charset=utf-8");
            return repoServicios.all();
        }, this.jsonTransformer);


        /** Get a specific service changelog **/

        get("/services/:name", "application/json;charset=utf-8", (request, response) -> {
            response.type("application/json;charset=utf-8");
            return repoServicios.get(request.params(":name"));
        }, this.jsonTransformer);


        /** Delete a specific service changelog **/

        delete("/services/:name", "application/json;charset=utf-8", (request, response) -> {
            response.type("application/json;charset=utf-8");
            repoServicios.delete(repoServicios.get(request.params(":name")));
            response.status(200);
            return new ResponseObject(200, "success");
        }, this.jsonTransformer);


        /** Create a service changelog **/

        post("/services/:name", "application/json;charset=utf-8", (request, response) -> {
            response.type("application/json;charset=utf-8");
            if(createService(request.body())) return new ResponseObject(200, "success");
            return new ResponseObject(500, "failure");
        }, this.jsonTransformer);


    }

    private Boolean createService(String body){

        Service service = gson.fromJson(body, Service.class);
        return repoServicios.add(service);
    }

}
