package de.fsmpi.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Marcel Fraas on 08.09.16.
 */

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByProductName(String productName);
}
