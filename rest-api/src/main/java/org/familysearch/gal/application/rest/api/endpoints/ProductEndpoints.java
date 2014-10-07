package org.familysearch.gal.application.rest.api.endpoints;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

public interface ProductEndpoints {

    /**
     * Responsible to give Product information
     */
    @GET
    @Path("/{productid}")
    @Produces({ APPLICATION_JSON })
    Response read(@Context Request request, @PathParam("productid") UUID productId);
}
