package org.familysearch.gal.application.rest.api.endpoints.impl;

import org.apache.log4j.Logger;
import org.familysearch.engage.foundation.util.LinkBuilderFactory;
import org.familysearch.gal.application.rest.api.endpoints.ProductEndpoints;
import org.familysearch.gal.application.rest.api.model.ProductRepresentation;
import org.familysearch.gal.application.service.api.ProductResourceService;
import org.familysearch.gal.application.service.api.model.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Path;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

@Component
@Path("/products")
public class ProductEndpointsImpl implements ProductEndpoints {

    private static final Logger LOGGER = Logger
            .getLogger(ProductEndpointsImpl.class);

    @Autowired
    private ProductResourceService productResourceService;

    @Autowired
    private LinkBuilderFactory linkBuilderFactory;

    @Override
    public Response read(Request request, UUID productId) {
        Product product = productResourceService.getProduct(productId);
        if(product!=null) {
            ProductRepresentation productRepresentation = new ProductRepresentation();
            BeanUtils.copyProperties(product, productRepresentation);
            return Response.ok(productRepresentation).build();
        }
        else {
            return Response.noContent().build();
        }
    
    }

    @Override
    public Response create(Request request,
            ProductRepresentation productRepresentation) {
        Response response = null;
        Product product = mapToModel(productRepresentation);
        product = productResourceService.createProduct(product);
        BeanUtils.copyProperties(product, productRepresentation);
        String productLinkURI = linkBuilderFactory.newBuilder()
                .path(ProductEndpointsImpl.class)
                .path(ProductEndpoints.class, "read")
                .build(product.getUuid().toString());
        try {
            response = Response.created(new URI(productLinkURI)).build();
        } catch (URISyntaxException e) {
            response = Response.serverError().build();
        }
        return response;
    }

    @Override
    public Response update(Request request, UUID productId,
            ProductRepresentation productRepresentation) {
        Product product = mapToModel(productRepresentation);
        Boolean status = productResourceService.updateProduct(productId, product);
        String productLinkURI = linkBuilderFactory.newBuilder()
                .path(ProductEndpointsImpl.class)
                .path(ProductEndpoints.class, "read")
                .build(productId.toString());
        if (status) {
            return Response.ok("Product updated successfully").header(HttpHeaders.LOCATION, productLinkURI).build();
        } else {
            LOGGER.info("The product update failed");
            return Response.serverError().build();
        }
    }

    @Override
    public Response delete(Request request, UUID productId) {
        Boolean status = productResourceService.deleteProduct(productId);
        if (status) {
            return Response.noContent().build();
        } else {
            LOGGER.info("The product deletes failed");
            return Response.serverError().build();
        }
    }

    Product mapToModel(ProductRepresentation productRepresentation) {
        Product product = new Product();
        BeanUtils.copyProperties(productRepresentation, product);
        return product;
    }

    public ProductResourceService getProductResourceService() {
        return productResourceService;
    }

    public void setProductResourceService(
            ProductResourceService productResourceService) {
        this.productResourceService = productResourceService;
    }

    public LinkBuilderFactory getLinkBuilderFactory() {
        return linkBuilderFactory;
    }

    public void setLinkBuilderFactory(LinkBuilderFactory linkBuilderFactory) {
        this.linkBuilderFactory = linkBuilderFactory;
    }

}
