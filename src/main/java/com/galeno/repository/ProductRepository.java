package com.galeno.repository;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.galeno.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductRepository extends EntityGraphJpaRepository<Product, Long> {

    Page<Product> findAllByNameContaining(String name, Pageable pageable, EntityGraph entityGraph);
}
