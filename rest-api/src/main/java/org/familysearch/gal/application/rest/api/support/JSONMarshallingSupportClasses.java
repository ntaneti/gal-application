package org.familysearch.gal.application.rest.api.support;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import org.familysearch.gal.application.rest.api.model.ProductRepresentation;
import org.springframework.stereotype.Component;


@Component
public class JSONMarshallingSupportClasses {

    private static List<MediaType> mediaTypes = Arrays
        .asList(new MediaType[] { GalMediaTypes.APPLICATION_GAL_V1_JSON_TYPE});

    private static abstract class GALV1_JSONSupport<T> extends BaseJSONSupport<T> {
        @Override
        protected List<MediaType> getMediaTypes() {
            return mediaTypes;
        }
    }

    @Provider
    @Produces({ GalMediaTypes.APPLICATION_GAL_V1_JSON })
    public static class ProductRepresentationJSONSupport extends GALV1_JSONSupport<ProductRepresentation> {
        @Override
        protected Class<ProductRepresentation> getTypeToken() {
            return ProductRepresentation.class;
        }
    }

}
