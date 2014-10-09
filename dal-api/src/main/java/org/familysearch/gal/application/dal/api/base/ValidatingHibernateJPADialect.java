/**
 * Copyright © 2010 Intellectual Reserve, Inc. All Rights reserved.
 */

package org.familysearch.gal.application.dal.api.base;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;

/**
 * Extends {@link HibernateJpaDialect} to translate {@link javax.validation.ValidationException} to Spring's
 * {@link DataIntegrityViolationException}.
 * 
 * @author gakakarlapudi
 * 
 * 
 */
public class ValidatingHibernateJPADialect extends HibernateJpaDialect {

    private static final long serialVersionUID = 1017831111664666555L;

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.orm.jpa.vendor.HibernateJpaDialect#translateExceptionIfPossible(java.lang.RuntimeException)
     */
    @Override
    public DataAccessException translateExceptionIfPossible(RuntimeException ex) {
        if (ex instanceof ValidationException) {
            String message = ex.getMessage();

            // If this is a "ConstraintViolation" exception, extract the details of
            // the violation and set in the new exception
            if (ex instanceof ConstraintViolationException) {
                StringBuilder sb = new StringBuilder();
                ConstraintViolationException cve = (ConstraintViolationException) ex;
                for (ConstraintViolation<?> viol : cve.getConstraintViolations()) {
                    if (sb.length() > 0) {
                        sb.append("\n");
                    }
                    sb.append("'").append(viol.getPropertyPath()).append("' ");
                    sb.append(viol.getMessage());
                    sb.append(" --> ");
                    sb.append(viol.getInvalidValue());
                }
                message = sb.toString();
            }

            return new DataIntegrityViolationException(message, ex);
        }

        return super.translateExceptionIfPossible(ex);
    }

}
