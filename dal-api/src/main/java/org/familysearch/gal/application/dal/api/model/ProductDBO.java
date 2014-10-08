/**
 * Copyright © 2010 Intellectual Reserve, Inc. All Rights reserved.
 */

package org.familysearch.gal.application.dal.api.model;

import java.util.Calendar;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.familysearch.gal.application.dal.api.base.AbstractEntity;
import org.familysearch.gal.application.dal.api.util.GenericModelValidator;
import org.familysearch.gal.application.dal.api.util.ModelValidator;
import org.hibernate.validator.constraints.Length;

/**
 * Represents product entry
 * 
 * @author gakakarlapudi
 * 
 */
@Entity
@Table(name = "products", uniqueConstraints = @UniqueConstraint(columnNames = { "uuid" }))
public class ProductDBO extends AbstractEntity<ProductDBO> {

    private static final long serialVersionUID = 873245192978870789L;

    private static final ModelValidator<ProductDBO> validator = new GenericModelValidator<ProductDBO>();

    private UUID uuid = UUID.randomUUID();
    private Long id;
    private String name;
    private String description;
    private Calendar creationTime;
    private Calendar lastUpdateTime;
    
    /**
     * Default constructor
     */
    public ProductDBO() {

    }

    /**
     * Parametrized Constructor
     */
    public ProductDBO(ProductDBO other) {
        this.uuid = other.getUuid();
        this.name = other.getName();
        this.description = other.getDescription();
        this.creationTime = other.getCreationTime();
        this.lastUpdateTime = other.getLastUpdateTime();
    }

    /**
     * {@inheritDoc}
     */
    protected ModelValidator<ProductDBO> validator() {
        return validator;
    }

    /**
     * Gets uuid.
     * 
     * @return Value of uuid.
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * Sets new uuid.
     * 
     * @param uuid New value of uuid.
     */
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * @return the productId
     */
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @org.hibernate.annotations.GenericGenerator(name = "product_seq", strategy = "org.hibernate.id.enhanced.TableGenerator",
                    parameters = {
                                  @org.hibernate.annotations.Parameter(name = "segment_value", value = "product_seq"),
                                  @org.hibernate.annotations.Parameter(name = "initial_value", value = "50"),
                                  @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
                                  @org.hibernate.annotations.Parameter(name = "optimizer", value = "none") })
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the creationTime
     */
    @NotNull
    @Column(name = "creationTime", insertable = true, nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Calendar getCreationTime() {
        return creationTime;
    }

    /**
     * @param creationTime
     *            the creationTime to set
     */
    public void setCreationTime(Calendar creationTime) {
        this.creationTime = creationTime;
    }

    @NotNull
    @Column(name = "lastUpdateTime", insertable = true, nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Calendar getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Calendar lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 0;
		result  = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ProductDBO)) {
			return false;
		}
		ProductDBO other = (ProductDBO) obj;
		if (uuid == null) {
			if (other.getUuid() != null) {
				return false;
			}
		} else if (!uuid.equals(other.getUuid())) {
			return false;
		}
		return true;
	}

	@NotNull
	@Length(max = 255)
	@Column(name = "name", length = 255, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotNull
	@Length(max = 255)
	@Column(name = "description", length = 255, nullable = false)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
