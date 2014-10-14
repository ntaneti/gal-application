package org.familysearch.gal.application.rest.api.endpoints.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import javax.ws.rs.Path;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.familysearch.engage.foundation.util.LinkBuilderFactory;
import org.familysearch.gal.application.rest.api.endpoints.ApplicationEndpoints;
import org.familysearch.gal.application.rest.api.model.ApplicationRepresentation;
import org.familysearch.gal.application.service.api.ApplicationResourceService;
import org.familysearch.gal.application.service.api.model.Application;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/applications")
public class ApplicationEndpointsImpl implements ApplicationEndpoints {

	private static final Logger LOGGER = Logger
			.getLogger(ApplicationEndpointsImpl.class);

	@Autowired
	private ApplicationResourceService applicationResourceService;

	@Autowired
	private LinkBuilderFactory linkBuilderFactory;

	public ApplicationResourceService getApplicationResourceService() {
		return applicationResourceService;
	}

	public void setApplicationResourceService(
			ApplicationResourceService applicationResourceService) {
		this.applicationResourceService = applicationResourceService;
	}

	public LinkBuilderFactory getLinkBuilderFactory() {
		return linkBuilderFactory;
	}

	public void setLinkBuilderFactory(LinkBuilderFactory linkBuilderFactory) {
		this.linkBuilderFactory = linkBuilderFactory;
	}

	@Override
	public Response read(Request request, UUID applicationId) {
		Application application = applicationResourceService
				.getApplication(applicationId);
		if (application != null) {
			ApplicationRepresentation applicationRepresentation = new ApplicationRepresentation();
			BeanUtils.copyProperties(application, applicationRepresentation);
			applicationRepresentation.setCreationTime(application.getCreationTime().getTime());
			applicationRepresentation.setLastUpdateTime(application.getCreationTime().getTime());
			applicationRepresentation.setAuthorId(UUID.randomUUID());
			return Response.ok(applicationRepresentation).build();
		} else {
			return Response.noContent().build();
		}

	}

	@Override
	public Response delete(Request request, UUID applictaionId) {
		Boolean status = applicationResourceService
				.deleteApplication(applictaionId);
		if (status) {
			return Response.ok("The application got deleted successfully")
					.build();
		} else {
			LOGGER.info("The application deletes failed");
			return Response.serverError().build();
		}
	}

	@Override
	public Response create(Request request,
			ApplicationRepresentation applicationRepresentation) {
		Response response = null;
		Application application = new Application();
		BeanUtils.copyProperties(applicationRepresentation, application);
		application = applicationResourceService.createApplication(application);
		BeanUtils.copyProperties(application, applicationRepresentation);
		String applicationLinkURI = linkBuilderFactory.newBuilder()
				.path(ApplicationEndpointsImpl.class)
				.path(ApplicationEndpoints.class, "read")
				.build(application.getUuid().toString());
		try {
			response = Response.created(new URI(applicationLinkURI)).build();
		} catch (URISyntaxException e) {
			response = Response.serverError().build();
		}
		return response;
	}

	@Override
	public Response update(Request request, UUID applicationId,
			ApplicationRepresentation applicationRepresentation) {
		Application application = new Application();
		BeanUtils.copyProperties(applicationRepresentation, application);
		Boolean status = applicationResourceService.updateApplication(
				applicationId, application);
		String productLinkURI = linkBuilderFactory.newBuilder()
				.path(ApplicationEndpointsImpl.class)
				.path(ApplicationEndpoints.class, "read")
				.build(applicationId.toString());
		if (status) {
			return Response.ok("Application updated successfully")
					.header(HttpHeaders.LOCATION, productLinkURI).build();
		} else {
			LOGGER.info("The Application update failed");
			return Response.serverError().build();
		}
	}

}
