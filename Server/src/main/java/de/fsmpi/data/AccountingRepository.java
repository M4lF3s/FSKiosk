package de.fsmpi.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Marcel Fraas on 30.08.16.
 */

@RepositoryRestResource
public interface AccountingRepository extends JpaRepository<Accounting, Long> {

    @Query("SELECT a FROM Accounting a WHERE a.timestamp = (SELECT max(a1.timestamp) FROM Accounting a1)")
    Accounting findLast();

}
