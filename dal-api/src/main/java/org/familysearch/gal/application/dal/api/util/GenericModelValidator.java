/*
 * Copyright © 2010 Intellectual Reserve, Inc. All Rights reserved.
 */
package org.familysearch.gal.application.dal.api.util;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.Logger;

/**
 * Provides object model validation.
 * 
 * @author gakakarlapudi
 * 
 */
public class GenericModelValidator<T>
                implements ModelValidator<T> {

    private static final Logger log = Logger.getLogger(GenericModelValidator.class);

    /*
     * DEFAULT_ONLY is a pill swallowed by validateInstance() to
     * indicate that the javax.validation.groups.Default validation
     * group is the only group to validate against.
     */
    private static interface DefaultValidationGroupOnly {
    }

    private static final Class<?>[] DEFAULT_ONLY = { DefaultValidationGroupOnly.class };

    final Validator validator;

    public GenericModelValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(T instance) {
        return isValid(instance, DEFAULT_ONLY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(T instance, Class<?>... groups) {
        Set<ConstraintViolation<T>> violations = validateInstance(instance, groups);

        if (violations == null || violations.isEmpty())
            return true;

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(T instance) {
        validate(instance, DEFAULT_ONLY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(T instance, Class<?>... groups) {
        Set<ConstraintViolation<T>> violations = validateInstance(instance, groups);
        if (violations != null && !violations.isEmpty()) {
            Set<ConstraintViolation<?>> constraintViolations = new HashSet<ConstraintViolation<?>>();
            constraintViolations.addAll(violations);
            throw new ModelValidationException(constraintViolations);
        }
    }

    /**
     * Validate the instance.
     * 
     * @param instance
     * @return
     */
    Set<ConstraintViolation<T>> validateInstance(T instance, Class<?>... groups) {
        Set<ConstraintViolation<T>> violations;

        if (groups == null || groups == DEFAULT_ONLY) {
            violations = validator.validate(instance);
        }
        else {
            violations = validator.validate(instance, groups);
        }

        if (log.isDebugEnabled()) {
            for (ConstraintViolation<T> violation : violations) {
                log.debug(violation);
            }
        }

        return violations;
    }
}
