package ar.com.osde.esb.component.changelogManager.app;


import ar.com.osde.esb.component.changelogManager.app.controller.ComponentController;
import ar.com.osde.esb.component.changelogManager.app.controller.ServiceController;
import ar.com.osde.esb.component.changelogManager.app.utils.JsonTransformer;
import ar.com.osde.esb.component.changelogManager.app.utils.ServerConfig;
import ar.com.osde.esb.component.changelogManager.domain.Component;
import ar.com.osde.esb.component.changelogManager.domain.Service;
import ar.com.osde.esb.component.changelogManager.domain.Version;
import ar.com.osde.esb.component.changelogManager.domain.repository.ComponentRepository;
import ar.com.osde.esb.component.changelogManager.persistence.ConnectionManager;
import com.google.gson.Gson;
import com.mongodb.MongoException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


public class Server {

    private static ComponentRepository repoComponentes = ComponentRepository.getInstance();

    public static void main(String[] args ) throws UnknownHostException, MongoException {

       /* Configure connection to mongo DB */
       ConnectionManager.init();

       /* Configure spark server */
       ServerConfig.configureServer(8000, "/webapp");

       /* Configure controllers */
       Gson gson = new Gson();
       JsonTransformer jsonTransformer = new JsonTransformer(gson);
       new ServiceController(gson, jsonTransformer).register();
       new ComponentController(gson, jsonTransformer).register();

        /* Create some data */
        List<Version> versiones = new ArrayList<>();
        versiones.add(new Version("1.0.0", "Version inicial"));
        versiones.add(new Version("1.1.0", "Cambios en la logica"));
        versiones.add(new Version("1.2.0", "Correccion de dependencias"));

        Service socio = new Service("Socio");
        socio.setVersions(versiones);

        Service subsidio = new Service("SubsidioServicePort");
        subsidio.setVersions(versiones);

        Component interceptors = new Component("osde-esb-camel-interceptors");
        interceptors.setVersions(versiones);

        Component appName = new Component("osde-esb-security-appName");
        appName.setVersions(versiones);

        /* Save data */
        /* repoServicios.add(socio);
        repoServicios.add(subsidio); */

        /* repoComponentes.add(interceptors);
        repoComponentes.add(appName); */

        /* Get data */
        /*System.out.println(repoServicios.get("socio").getName());
        System.out.println(repoComponentes.get("osde-esb-camel-interceptors").getName()); */

        /* Delete data */
        /* repoServicios.delete(socio); **/

        /* List data */
        /* System.out.println(repoServicios.all());
        System.out.println(repoComponentes.all()); */
    }
}
