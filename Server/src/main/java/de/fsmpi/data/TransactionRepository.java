package de.fsmpi.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

/**
 * Created by Marcel Fraas on 26.08.16.
 */

@RepositoryRestResource
public interface TransactionRepository extends JpaRepository<Transaction, Long> {


    @Query("SELECT t FROM Transaction t WHERE t.timestamp > :startingDate")
    List<Transaction> find(@Param("startingDate") @Temporal(TemporalType.TIMESTAMP) Date startingDate);
    //List<Transaction> findByTimestamp(@Temporal(TemporalType.DATE) Date startingDate);


}
