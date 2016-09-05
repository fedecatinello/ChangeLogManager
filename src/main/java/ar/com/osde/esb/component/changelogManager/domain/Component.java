package ar.com.osde.esb.component.changelogManager.domain;

import org.mongodb.morphia.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(value = "componentes", noClassnameStored = true)
@Indexes(
        @Index(value = "nombreComponente", fields = @Field("nombre"))
)
public class Component {

    @Id
    private UUID id;
    @Property("nombre")
    private String name;
    @Embedded
    private List<Version> versions;

    public Component(String name) {
        this.setId(UUID.randomUUID());
        this.setName(name);
        this.setVersions(new ArrayList<>());
    }

    /* Empty constructor */
    public Component(){}

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
