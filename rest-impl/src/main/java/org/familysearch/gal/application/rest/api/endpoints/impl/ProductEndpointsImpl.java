package org.familysearch.gal.application.rest.api.endpoints.impl;

import java.util.UUID;

import javax.ws.rs.Path;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import org.familysearch.gal.application.rest.api.endpoints.ProductEndpoints;
import org.springframework.stereotype.Component;

@Component
@Path("/products")
public class ProductEndpointsImpl implements ProductEndpoints {

    @Override
    public Response read(Request request, UUID productId) {
        // TODO : dummy implementation
        return Response.ok().build();
    }

}
