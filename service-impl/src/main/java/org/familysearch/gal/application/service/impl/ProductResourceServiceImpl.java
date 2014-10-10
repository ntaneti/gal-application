package org.familysearch.gal.application.service.impl;

import java.util.Calendar;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.familysearch.gal.application.dal.api.model.ProductDBO;
import org.familysearch.gal.application.dal.api.repositories.ProductRepository;
import org.familysearch.gal.application.service.api.ProductResourceService;
import org.familysearch.gal.application.service.api.model.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation for
 * {@link org.familysearch.gal.application.service.api.ProductResourceService}
 */
@Component
@Transactional("transactionManager")
public class ProductResourceServiceImpl implements ProductResourceService {

	/** Logger */
	private static final Logger LOGGER = Logger
			.getLogger(ProductResourceServiceImpl.class);

	@Autowired
	private ProductRepository productRepository;

	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public Product getProduct(UUID productId) {
		Product product = null;
		ProductDBO productDBO = productRepository.findByUuid(productId);
		if (productDBO != null) {
			product = new Product();
			BeanUtils.copyProperties(productDBO, product);
		}
		return product;

	}

	@Override
	public Product createProduct(Product product) {
		ProductDBO productDBO = new ProductDBO();
		productDBO.setName(product.getName());
		productDBO.setDescription(product.getDescription());
		productDBO.setCreationTime(Calendar.getInstance());
		productDBO.setLastUpdateTime(Calendar.getInstance());
		productDBO = productRepository.save(productDBO);
		BeanUtils.copyProperties(productDBO, product);
		return product;
	}

	@Override
	public Boolean updateProduct(UUID productId, Product product) {
		ProductDBO productDBO = productRepository.findByUuid(productId);
		productDBO.setName(product.getName());
		productDBO.setDescription(product.getDescription());
		productDBO.setLastUpdateTime(Calendar.getInstance());
		try {
			productDBO = productRepository.saveAndFlush(productDBO);
			return true;
		} catch (Exception e) {
			LOGGER.info("failed to update");
			return false;
		}

	}

	@Override
	public boolean deleteProduct(UUID productId) {
		ProductDBO productDBO = productRepository.findByUuid(productId);
		try {
			productRepository.delete(productDBO);
			return true;
		} catch (Exception e) {
			LOGGER.info("failed to delete");
			return false;
		}
	}

}
