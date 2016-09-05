package ar.com.osde.esb.component.changelogManager.domain;


import org.mongodb.morphia.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(value = "servicios", noClassnameStored = true)
@Indexes(
        @Index(value = "nombreServicio", fields = @Field("nombre"))
)
public class Service {

    @Id
    private UUID id;
    @Property("nombre")
    private String name;
    @Embedded
    private List<Version> versions;

    public Service(String name) {
        this.setId(UUID.randomUUID());
        this.setName(name);
        this.setVersions(new ArrayList<>());
    }

    /* Empty constructor */
    public Service(){}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Version> getVersions() {
        return versions;
    }

    public void setVersions(List<Version> versions) {
        this.versions = versions;
    }
}
