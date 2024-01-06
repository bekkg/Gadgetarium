package com.peaksoft.gadgetarium.repository;

import com.peaksoft.gadgetarium.model.entities.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends JpaRepository<Basket,Long> {
}
