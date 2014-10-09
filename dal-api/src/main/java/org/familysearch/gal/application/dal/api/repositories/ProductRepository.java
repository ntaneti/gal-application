package org.familysearch.gal.application.dal.api.repositories;

import java.util.UUID;

import org.familysearch.gal.application.dal.api.model.ProductDBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to for {@link org.familysearch.gal.application.dal.api.model.ProductDBO}
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductDBO, Long>, JpaSpecificationExecutor,
                PagingAndSortingRepository<ProductDBO, Long> {
	
	ProductDBO findByUuid(UUID uuid);

    ProductDBO findByName(String name);

    ProductDBO findByNameAndDescription(String name, String description);
    
}
