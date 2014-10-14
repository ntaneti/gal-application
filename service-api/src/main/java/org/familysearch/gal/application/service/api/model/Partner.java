package org.familysearch.gal.application.service.api.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.familysearch.gal.application.dal.api.util.GenericModelValidator;
import org.familysearch.gal.application.dal.api.util.ModelValidator;


public class Partner {

    private static final long serialVersionUID = 1L;

    private static final ModelValidator<Partner> validator = new GenericModelValidator<Partner>();

    @NotNull private UUID uuid;
    @NotNull @Size(max = 128) private String name;
    @Size(max = 128) private String email;
    @Size(max = 45) private String website;
    @Size(max = 35) private String partnerType;
    private Calendar creationTime;
    private Calendar lastUpdateTime;
    private List<Application> applications = new ArrayList<Application>();;
    
    
    /**
     * {@inheritDoc}
     */
    protected ModelValidator<Partner> validator() {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getPartnerType() {
		return partnerType;
	}

	public void setPartnerType(String partnerType) {
		this.partnerType = partnerType;
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

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }
}
