package ar.com.osde.esb.component.changelogManager.domain;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;

@Embedded
public class Version {

    @Property("version")
    private String identifier;
    @Property("descripcion")
    private String description;

    public Version(String identifier, String description) {
        this.setIdentifier(identifier);
        this.setDescription(description);
    }

    /* Empty constructor */
    public Version(){}

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
