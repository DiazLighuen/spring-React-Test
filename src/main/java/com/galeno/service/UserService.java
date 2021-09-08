package com.galeno.service;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphUtils;
import com.galeno.exception.InvalidFilterCriteriaException;
import com.galeno.model.FilterCriteria;
import com.galeno.model.Product;
import com.galeno.model.User;
import com.galeno.repository.ProductRepository;
import com.galeno.repository.UserRepository;
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
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public EntityGraph generateEntityGraph(String... paths) {
        return paths == null || paths.length == 0 ? null : EntityGraphUtils.fromAttributePaths(paths);
    }

    @Transactional(readOnly = true)
    public List<User> findAll(String... with) {
        return new ArrayList<>((Collection<? extends User>) getUserRepository().findAll(generateEntityGraph(with)));
    }

    @Transactional(readOnly = true)
    public Page<User> findPage(int page, int pageSize, FilterCriteria filterCriteria, String filterValue, String... with) {
        if (filterCriteria == null){
            return getUserRepository().findAll(PageRequest.of(page, pageSize), generateEntityGraph(with));
        }
        else {
            if (filterCriteria == FilterCriteria.NAME){
                return getUserRepository().findAllByUsernameContaining(filterValue, PageRequest.of(page,pageSize), generateEntityGraph(with));
            }
        }
        throw new InvalidFilterCriteriaException();
    }

    @Transactional
    public User persist(User user) {
        return getUserRepository().save(user);
    }

    @Transactional
    public User getById(Long id, String... with){
        return findById(id, with).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Optional<User> findById(Long id, String... with) {
        return getUserRepository().findById(id,generateEntityGraph(with));
    }
}
