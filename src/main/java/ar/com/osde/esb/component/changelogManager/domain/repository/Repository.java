package ar.com.osde.esb.component.changelogManager.domain.repository;

import com.mongodb.WriteResult;
import org.mongodb.morphia.query.ValidationException;

import java.util.List;

public interface Repository<T> {

    /**
     * @apiNote  Return an entity given a specific name
     *
     **/
    T get(String name);

    /**
     * @apiNote  Delete an entity
     *
     **/
    void delete(T entity);

    /**
     * @apiNote  Save an entity
     *
     **/
    void add(T entity);

    /**
     * @apiNote  List all collection elements
     *
     **/
    List<T> all();

    /**
     * @apiNote  Validate when a document is insert, update or delete
     *
     **/
    default Boolean validate(WriteResult result){
        if(result.getN() != 0)
            return true;

        else
            throw new ValidationException("Validation has thrown errors");
    }
}
