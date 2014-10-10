package org.familysearch.gal.application.rest.api.endpoints;

import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import org.familysearch.gal.application.rest.api.model.ProductRepresentation;
import org.familysearch.gal.application.rest.api.support.GalMediaTypes;

public interface ProductEndpoints {

    /**
     * Responsible to give Product information
     */
    @GET
    @Path("/{productid}")
    @Produces({ GalMediaTypes.APPLICATION_GAL_V1_JSON })
    public Response read(@Context Request request, @PathParam("productid") UUID productId);
    
    @POST
    @Path("/")
    @Produces({ GalMediaTypes.APPLICATION_GAL_V1_JSON })
    @Consumes({ GalMediaTypes.APPLICATION_GAL_V1_JSON })
    public Response create(@Context Request request,
                           ProductRepresentation productRepresentation);
    
    @PUT
    @Path("/{productid}")
    @Produces({ GalMediaTypes.APPLICATION_GAL_V1_JSON})
    @Consumes({ GalMediaTypes.APPLICATION_GAL_V1_JSON })
    public Response update(@Context Request request, @PathParam("productid") UUID productId, ProductRepresentation productRepresentation);
    
    @DELETE
    @Path("/{productid}")
    @Produces({ GalMediaTypes.APPLICATION_GAL_V1_JSON })
    public Response delete(@Context Request request, @PathParam("productid") UUID productId);
}
