package com.galeno.repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.galeno.model.Cart;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends EntityGraphJpaRepository<Cart, Long> {
}
