package ar.com.osde.esb.component.changelogManager.domain.repository;

import ar.com.osde.esb.component.changelogManager.domain.Service;
import ar.com.osde.esb.component.changelogManager.persistence.ConnectionManager;
import com.mongodb.WriteResult;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import java.util.List;

public class ServiceRepository implements Repository<Service> {

    private Datastore db = ConnectionManager.getConnection();
    private static ServiceRepository instance;

    public static ServiceRepository getInstance() {

        if(instance == null) return new ServiceRepository();

        return instance;
    }
    @Override
    public Service get(String name) {
        Query<Service> query = db.createQuery(Service.class)
                                    .field("nombre")
                                    .equalIgnoreCase(name);
        return query.get();
    }

    @Override
    public void delete(Service entity) {
        Query<Service> query = db.createQuery(Service.class)
                                    .field("nombre")
                                    .equalIgnoreCase(entity.getName());

        WriteResult result = db.delete(query);
        validate(result);
    }


    @Override
    public void add(Service entity) {
        db.save(entity);
    }

    @Override
    public List<Service> all() {
        Query<Service> query = db.createQuery(Service.class);
        return query.asList();
    }

}
