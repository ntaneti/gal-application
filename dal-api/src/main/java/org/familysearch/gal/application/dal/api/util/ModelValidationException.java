/*
 * Copyright © 2010 Intellectual Reserve, Inc. All Rights reserved.
 */
package org.familysearch.gal.application.dal.api.util;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * Extends {@link ConstraintViolationException} to print the constraint
 * violations.
 * 
 * @author gakakarlapudi
 * 
 */
public class ModelValidationException extends ConstraintViolationException {

    private static final long serialVersionUID = 1L;

    /**
     * @param constraintViolations
     */
    public ModelValidationException(Set<ConstraintViolation<?>> constraintViolations) {
        super(constraintViolations);
    }

    /**
     * @param message
     * @param constraintViolations
     */
    public ModelValidationException(String message, Set<ConstraintViolation<?>> constraintViolations) {
        super(message, constraintViolations);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder buff = new StringBuilder(super.toString());
        buff.append("; Violations: [");
        for (ConstraintViolation<?> violation : getConstraintViolations()) {
            buff.append(violation).append("; ");
        }
        buff.append("]");

        return buff.toString();
    }

}
