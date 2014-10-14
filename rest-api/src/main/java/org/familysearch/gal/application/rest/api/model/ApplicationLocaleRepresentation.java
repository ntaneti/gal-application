package org.familysearch.gal.application.rest.api.model;

import java.util.Locale;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "applicationLocale")
@XmlType(name = "applicationLocale")
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
public class ApplicationLocaleRepresentation {

	private Locale locale;
	private String title;
	private String description;
	private String appSummary;

    @XmlJavaTypeAdapter(org.familysearch.gal.shared.rest.support.adapter.JAXBLocaleAdapter.class)
	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    public String getAppSummary() {
        return appSummary;
    }

    public void setAppSummary(String appSummary) {
        this.appSummary = appSummary;
    }

}
