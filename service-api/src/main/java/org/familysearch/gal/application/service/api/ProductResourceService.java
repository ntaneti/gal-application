package org.familysearch.gal.application.service.api;

import java.util.UUID;

import org.familysearch.gal.application.service.api.model.Product;

/**
 * Resource Services for Product
 */
public interface ProductResourceService {


    /**
     * Retrieve the product which is identified by the unique product identifier
     * 
     * @param productId unique product identifier
     * @return {@link org.familysearch.gal.application.service.api.model.Product} corresponding to given product id.
     */
    Product getProduct(UUID productId);

    /**
     * Responsible to create the product from the given {@link org.familysearch.gal.application.service.api.model.Product}
     * 
     * @param product product to be created
     * @return Created {@link org.familysearch.gal.application.service.api.model.Product}
     */
    Product createProduct(Product product);    

    
    /**
     * Responsible to update the product with new details in the product
     *
     * @param product product to be updated
     * @return TRUE on successful update of product
     */
    Boolean updateProduct(UUID productId, Product product);

    /**
     * Responsible to delete products based on the given product identifier
     * 
     * @param productId unique product identifier
     * @return TRUE of delete successful otherwise FALSE
     */
    boolean deleteProduct(UUID productId);

}

