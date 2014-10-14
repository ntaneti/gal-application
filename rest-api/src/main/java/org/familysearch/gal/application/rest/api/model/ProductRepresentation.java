package org.familysearch.gal.application.rest.api.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.familysearch.gal.shared.model.AbstractRepresentation;

@XmlRootElement
public class ProductRepresentation extends AbstractRepresentation {

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
