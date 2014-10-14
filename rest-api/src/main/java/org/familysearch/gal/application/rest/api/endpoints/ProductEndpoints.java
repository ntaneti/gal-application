package org.familysearch.gal.application.rest.api.endpoints;

import static org.familysearch.gal.shared.common.GALMediaTypes.APPLICATION_GAL_JSON;

import java.util.UUID;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import org.familysearch.gal.application.rest.api.model.ProductRepresentation;

public interface ProductEndpoints {

    /**
     * Responsible to give Product information
     */
    @GET
    @Path("/{productid}")
    @Produces({ APPLICATION_GAL_JSON, MediaType.APPLICATION_JSON})
    public Response read(@Context Request request, @PathParam("productid") UUID productId);
    
    @POST
    @Path("/")
    @Produces({ APPLICATION_GAL_JSON, MediaType.APPLICATION_JSON })
    @Consumes({ APPLICATION_GAL_JSON, MediaType.APPLICATION_JSON })
    public Response create(@Context Request request,
                           ProductRepresentation productRepresentation);
    
    @PUT
    @Path("/{productid}")
    @Produces({ APPLICATION_GAL_JSON, MediaType.APPLICATION_JSON })
    @Consumes({ APPLICATION_GAL_JSON, MediaType.APPLICATION_JSON })
    public Response update(@Context Request request, @PathParam("productid") UUID productId, ProductRepresentation productRepresentation);
    
    @DELETE
    @Path("/{productid}")
    @Produces({ APPLICATION_GAL_JSON, MediaType.APPLICATION_JSON })
    public Response delete(@Context Request request, @PathParam("productid") UUID productId);
}
