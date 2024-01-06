package com.peaksoft.gadgetarium.repository;

import com.peaksoft.gadgetarium.model.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
