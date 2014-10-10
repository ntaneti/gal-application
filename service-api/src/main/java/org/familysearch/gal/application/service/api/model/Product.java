package org.familysearch.gal.application.service.api.model;

import java.util.Calendar;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.familysearch.gal.application.dal.api.util.GenericModelValidator;
import org.familysearch.gal.application.dal.api.util.ModelValidator;


public class Product {

    private static final long serialVersionUID = 1L;

    private static final ModelValidator<Product> validator = new GenericModelValidator<Product>();

    @NotNull private UUID uuid;
    @NotNull @Size(max = 255) private String name;
    @Size(max = 255) private String description;
    private Calendar creationTime;
    private Calendar lastUpdateTime;

    /**
     * {@inheritDoc}
     */
    protected ModelValidator<Product> validator() {
        return validator;
    }

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

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

	public Calendar getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Calendar creationTime) {
		this.creationTime = creationTime;
	}

	public Calendar getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Calendar lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("uuid=").append(uuid);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
