package ar.com.osde.esb.component.changelogManager.persistence;


import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

public class ConnectionManager {

    /** Configure Singletons **/
    private static Morphia instance;
    private static Datastore dStore;

    public static Morphia getInstance(){

        if(instance == null) return new Morphia();

        return instance;
    }

    public static void init(){

        getInstance().mapPackage("ar.com.osde.esb.component.changelogManager.domain");
    }

    public static Datastore getConnection(){

        if(dStore == null) return getInstance().createDatastore(new MongoClient(), "changelog");

        return dStore;
    }
}
