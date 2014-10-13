package org.familysearch.gal.application.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.familysearch.gal.application.service.api.ApplicationResourceService;
import org.familysearch.gal.application.service.api.model.Application;
import org.familysearch.gal.application.service.api.model.ApplicationLocale;
import org.familysearch.gal.application.service.api.model.Partner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation for
 * {@link org.familysearch.gal.application.service.api.ProductResourceService}
 */
@Component
@Transactional("transactionManager")
public class ApplicationResourceServiceImpl implements ApplicationResourceService {

	/** Logger */
	private static final Logger LOGGER = Logger
			.getLogger(ApplicationResourceServiceImpl.class);

	@Override
	public Application getApplication(UUID applictaionId) {
		return createApplication();
	}

	@Override
	public Application createApplication(Application application) {
		return createApplication();
	}

	@Override
	public Boolean updateApplication(UUID applictaionId, Application application) {
		return true;
	}

	@Override
	public boolean deleteApplication(UUID applictaionId) {
		return true;
	}

	private Application createApplication() {
		Application application = new Application();
		application.setUuid(UUID.randomUUID());
		application.setAppName("Application 1");
		application.setAppStatus("Active");
		application.setAppVersion("beta");
		application.setAverageRating(3.5);
		application.setCreationTime(Calendar.getInstance());
		application.setLastUpdateTime(Calendar.getInstance());
		application.setDownloadLink("http://upload.wikimedia.org/wikipedia/commons/0/07/Honeycrisp-Apple.jpg");
		application.setPlatform("Windows");
		application.setPopularity(3);
		application.setRatingCount(5);
		
		// create partner
		Partner partner = new Partner();
		partner.setUuid(UUID.randomUUID());
		partner.setName("xyz");
		partner.setWebsite("google.com");
		partner.setEmail("xyz@gmail.com");
		partner.setPartnerType("Admin");
		partner.setCreationTime(Calendar.getInstance());
		partner.setLastUpdateTime(Calendar.getInstance());
			
		application.setPartner(partner);
		
		// create locale
		ApplicationLocale locale = new ApplicationLocale();
		locale.setLocale(Locale.ENGLISH);
		locale.setDescription("Description of locale");
		locale.setTitle("Application");
		
		List<ApplicationLocale> locales = new ArrayList<ApplicationLocale>();
		locales.add(locale);
		
		application.setLocales(locales);
		
		return application;
		
	}

    @Override
    public List<Application> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

}
