/**
 * Copyright © 2010 Intellectual Reserve, Inc. All Rights reserved.
 */

package org.familysearch.gal.application.dal.api.base;

import java.io.Serializable;

/**
 * Entity: an data model class that has a synthetic identifier (primary key).
 * 
 * @author gakakarlapudi
 * 
 */
public interface Entity
                extends Serializable, Comparable<Entity> {

    /**
     * Return the unique identifier of the entity.
     * 
     * @return id
     */
    Long getId();

    /**
     * Set the unique identifier of the entity.
     * 
     * @param id
     */
    void setId(Long id);
}
