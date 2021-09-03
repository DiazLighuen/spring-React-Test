package com.galeno.service;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphUtils;
import com.galeno.exception.InvalidFilterCriteriaException;
import com.galeno.model.FilterCriteria;
import com.galeno.model.Product;
import com.galeno.repository.ProductRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Getter
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public EntityGraph generateEntityGraph(String... paths) {
        return paths == null || paths.length == 0 ? null : EntityGraphUtils.fromAttributePaths(paths);
    }

    @Transactional(readOnly = true)
    public List<Product> findAll(String... with) {
        return new ArrayList<>((Collection<? extends Product>) getRepository().findAll(generateEntityGraph(with)));
    }

    @Transactional(readOnly = true)
    public Page<Product> findPage(int page, int pageSize, FilterCriteria filterCriteria, String filterValue, String... with) {
        if (filterCriteria == null){
            return getRepository().findAll(PageRequest.of(page, pageSize), generateEntityGraph(with));
        }
        else {
            if (filterCriteria == FilterCriteria.NAME){
                return getRepository().findAllByNameContaining(filterValue, PageRequest.of(page,pageSize), generateEntityGraph(with));
            }
        }
        throw new InvalidFilterCriteriaException();
    }

    @Transactional
    public Product persist(Product product) {
        return getRepository().save(product);
    }

    @Transactional
    public Product getById(Long id, String... with){
        return findById(id, with).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Optional<Product> findById(Long id, String... with) {
        return getRepository().findById(id,generateEntityGraph(with));
    }
}
