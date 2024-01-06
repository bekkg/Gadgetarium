package com.peaksoft.gadgetarium.repository;

import com.peaksoft.gadgetarium.model.entities.PayCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayCardRepository extends JpaRepository<PayCard,Long> {
}
