package ar.com.osde.esb.component.changelogManager.app.utils;

import com.google.gson.Gson;

import static spark.Spark.*;

public class ServerConfig {

    public static void configureServer(int serverPort, String staticResources){

        port(serverPort);
        staticFileLocation(staticResources);
    }

}
