package org.familysearch.gal.application.rest.api.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessOrder;

import org.codehaus.jackson.annotate.JsonProperty;
import org.familysearch.gal.shared.model.AbstractRepresentation;
import org.familysearch.gal.shared.model.Link;

@XmlRootElement(name = "application")
@XmlType(name = "application")
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
public class ApplicationRepresentation extends AbstractRepresentation{

	private UUID uuid;
	private Link partner;
	private String appStatus;
	private String appName;
	private String appVersion;
	private String downloadLink;
	private String platform;
	private double averageRating;
	private int ratingCount;
	private int popularity;
	private Date creationTime;
	private Date lastUpdateTime;

	private List<ApplicationLocaleRepresentation> locales = new ArrayList<ApplicationLocaleRepresentation>();

	public ApplicationRepresentation() {
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getAppStatus() {
		return appStatus;
	}

	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getDownloadLink() {
		return downloadLink;
	}

	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	public int getRatingCount() {
		return ratingCount;
	}

	public void setRatingCount(int ratingCount) {
		this.ratingCount = ratingCount;
	}

	public int getPopularity() {
		return popularity;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	@JsonProperty(value="locales")
	public List<ApplicationLocaleRepresentation> getLocales() {
		return locales;
	}

	public void setLocales(
			List<ApplicationLocaleRepresentation> locales) {
		this.locales = locales;
	}

	@XmlElement(name = "facet")
    public Link getPartner() {
        return partner;
    }

    public void setPartner(Link partner) {
        this.partner = partner;
    }

}
