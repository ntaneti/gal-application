/*
 * Copyright © 2010 Intellectual Reserve, Inc. All Rights reserved.
 */
package org.familysearch.gal.application.dal.api.util;

import javax.validation.ConstraintViolationException;
import javax.validation.groups.Default;

/**
 * Interface for domain model validators.
 * 
 * @author gakakarlapudi
 * 
 */
public interface ModelValidator<T> {

    /**
     * Returns true if the instance passes validation.
     * (Using the {@link Default} validation group.)
     * 
     * @param instance
     * @return true if instance is valid
     */
    boolean isValid(T instance);

    /**
     * Returns true if the instance passes validation for the
     * given validation groups.
     * 
     * @param instance
     * @param groups List of validation group classes
     * @return true if instance is valid
     */
    boolean isValid(T instance, Class<?>... groups);

    /**
     * Throws {@link ConstraintViolationException} if the instance
     * fails validation (for the {@link Default} validation group).
     * 
     * @param instance
     * @throws ConstraintViolationException if validation fails.
     */
    void validate(T instance);

    /**
     * Throws {@link ConstraintViolationException} if the instance
     * fails validation for the given validation groups.
     * 
     * @param instance
     * @param groups
     * @throws ConstraintViolationException if validation fails.
     */
    void validate(T instance, Class<?>... groups);

}
