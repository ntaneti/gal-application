package org.familysearch.gal.application.dal.api.repositories;

import java.util.UUID;

import org.familysearch.gal.application.dal.api.model.ApplicationDBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to for {@link org.familysearch.gal.application.dal.api.model.ApplicationDBO}
 */
@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationDBO, Long>, JpaSpecificationExecutor,
                PagingAndSortingRepository<ApplicationDBO, Long> {

    ApplicationDBO findByUuid(UUID uuid);

}
