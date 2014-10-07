package org.familysearch.gal.application.rest.api.model;

import org.codehaus.enunciate.json.JsonRootType;

import javax.xml.bind.annotation.XmlRootElement;

@JsonRootType
@XmlRootElement
public class ProductRepresentation {

    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
