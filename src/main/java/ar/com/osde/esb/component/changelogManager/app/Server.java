package ar.com.osde.esb.component.changelogManager.app;


import ar.com.osde.esb.component.changelogManager.domain.Component;
import ar.com.osde.esb.component.changelogManager.domain.Service;
import ar.com.osde.esb.component.changelogManager.domain.Version;
import ar.com.osde.esb.component.changelogManager.domain.repository.ComponentRepository;
import ar.com.osde.esb.component.changelogManager.domain.repository.ServiceRepository;
import ar.com.osde.esb.component.changelogManager.persistence.ConnectionManager;
import com.mongodb.MongoException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import static spark.Spark.*;

public class Server {

    /* Create repositories */
    private static ServiceRepository repoServicios = ServiceRepository.getInstance();
    private static ComponentRepository repoComponentes = ComponentRepository.getInstance();


    public static void main( String[] args ) throws UnknownHostException, MongoException {

        /* Configure connection to mongo DB */
        ConnectionManager.init();


        get("/services/:name", (req, res) -> {

            repoServicios.get(req.params(":name"));
        });

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
        repoServicios.add(subsidio);

        repoComponentes.add(interceptors);
        repoComponentes.add(appName); */

        /* Get data */
        System.out.println(repoServicios.get("socio").getName());
        System.out.println(repoComponentes.get("osde-esb-camel-interceptors").getName());

        /* Delete data */
        /* repoServicios.delete(socio); **/

        /* List data */
        /* System.out.println(repoServicios.all());
        System.out.println(repoComponentes.all()); */
    }
}
