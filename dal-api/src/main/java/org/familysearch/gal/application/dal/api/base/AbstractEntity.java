/**
 * Copyright © 2010 Intellectual Reserve, Inc. All Rights reserved.
 */

package org.familysearch.gal.application.dal.api.base;

import javax.persistence.Transient;
import javax.validation.ValidationException;

import org.apache.commons.lang.time.FastDateFormat;
import org.familysearch.gal.application.dal.api.util.ModelValidator;


/**
 * Base class for entity classes. Entity classes are data model classes
 * that have a Long synthetic identifier.
 * 
 * @author gakakarlapudi
 * 
 */
@SuppressWarnings("serial")
public abstract class AbstractEntity<T> implements Entity {

    // "FastDateFormat" is thread-safe, replaces "DateFormat", as in
    // DateFormat FULL_DATE_TIME_FORMAT = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
    protected static final FastDateFormat FULL_DATE_TIME_FORMAT =
                    FastDateFormat.getDateTimeInstance(FastDateFormat.FULL, FastDateFormat.FULL);

    /**
     * Hook method for concrete classes to return a {@link ModelValidator} instance.
     * 
     * @return model validator
     */
    protected abstract ModelValidator<T> validator();

    /**
     * Validate the instance with javax.validation.
     * 
     * @param instance instance
     * @throws ValidationException if the instance is not valid.
     */
    @SuppressWarnings("unchecked")
    public void validate() {
        validator().validate((T) this);
    }

    /**
     * Validate the instance with javax.validation; returning true
     * if valid, false otherwise.
     * 
     * @param instance instance
     * @return true if valid
     */
    @SuppressWarnings("unchecked")
    @Transient
    public boolean isValid() {
        return validator().isValid((T) this);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Entity other) {
        if (this == other)
            return 0; // same entity

        if (other == null)
            return 1; // nulls filter to the bottom

        if (getId() == null) {
            if (other.getId() != null)
                return -1;
            else
                return 0; // both ids are null
        } else if (other.getId() == null)
            return 1;
        else
            return getId().compareTo(other.getId());
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (this == obj) {
            return true;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        }

        Entity eObj = (Entity) obj;
        return this.compareTo(eObj) == 0;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result +
                ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

}
