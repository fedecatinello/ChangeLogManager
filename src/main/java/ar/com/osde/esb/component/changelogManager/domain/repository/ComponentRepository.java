package ar.com.osde.esb.component.changelogManager.domain.repository;

import ar.com.osde.esb.component.changelogManager.domain.Component;
import ar.com.osde.esb.component.changelogManager.persistence.ConnectionManager;
import com.mongodb.WriteResult;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import java.util.List;

public class ComponentRepository implements Repository<Component> {

    private Datastore db = ConnectionManager.getConnection();
    private static ComponentRepository instance;

    public static ComponentRepository getInstance() {

        if(instance == null) return new ComponentRepository();

        return instance;
    }

    @Override
    public Component get(String name) {
        Query<Component> query = db.createQuery(Component.class)
                                    .field("nombre")
                                    .equalIgnoreCase(name);
        return query.get();
    }

    @Override
    public void delete(Component entity) {
        Query<Component> query = db.createQuery(Component.class)
                                    .field("nombre")
                                    .equalIgnoreCase(entity.getName());

        WriteResult result = db.delete(query);
        validate(result);
    }

    @Override
    public void add(Component entity) {
        db.save(entity);
    }

    @Override
    public List<Component> all() {
        Query<Component> query = db.createQuery(Component.class);
        return query.asList();
    }

}
