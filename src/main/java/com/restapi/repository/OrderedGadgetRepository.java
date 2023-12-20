package com.restapi.repository;

import com.restapi.model.OrderedGadget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderedGadgetRepository extends JpaRepository<OrderedGadget, Long> {
    List<OrderedGadget> findByOrderId(Long orderId);

}
