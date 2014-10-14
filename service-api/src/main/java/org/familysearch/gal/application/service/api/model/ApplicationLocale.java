package org.familysearch.gal.application.service.api.model;

import java.util.Locale;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.familysearch.gal.application.dal.api.util.GenericModelValidator;
import org.familysearch.gal.application.dal.api.util.ModelValidator;

public class ApplicationLocale {

    private static final long serialVersionUID = 1L;

    private static final ModelValidator<ApplicationLocale> validator = new GenericModelValidator<ApplicationLocale>();

    private Locale locale;
    @NotNull
    @Size(max = 256)
    private String title;
    private String description;
    private String appSummary;

    /**
     * Default constructor.
     */
    public ApplicationLocale() {
    }

    /**
     * Copy constructor.
     * 
     * @param that instance to copy
     */
    public ApplicationLocale(ApplicationLocale that) {
        this.description = that.getDescription();
        this.locale = that.getLocale();
        this.title = that.getTitle();
        this.appSummary = that.getAppSummary();
    }

    /**
     * {@inheritDoc}
     */
    protected ModelValidator<ApplicationLocale> validator() {
        return validator;
    }

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

    @Override
    public String toString() {
        return "ApplicationLocale [locale=" + locale + ", title=" + title + ", description=" + description
               + ", appSummary=" + appSummary + "]";
    }

}
