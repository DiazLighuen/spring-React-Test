package com.galeno.service;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphUtils;
import com.galeno.dto.CartDTO;
import com.galeno.dto.UserDTO;
import com.galeno.exception.InvalidFilterCriteriaException;
import com.galeno.model.Cart;
import com.galeno.model.FilterCriteria;
import com.galeno.model.User;
import com.galeno.repository.UserRepository;
import com.galeno.utils.Helper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
@Getter
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private Helper helper;

    public void toggleVIP(UserDTO userDTO){
        User user = getById(userDTO.getId());
        user.setUserVip(!user.getUserVip());
        userRepository.save(user);
    }

    public void saveCartPaid(User user){
        user.getCarts().add(user.getCart());
        user.setCart(getCartService().createNewCart(user));
        this.persist(user);
    }

    public void deleteCart(UserDTO userDTO){
        User user = getById(userDTO.getId());
        Cart cart = user.getCart();
        if(cart != null){
            getCartService().deleteCart(cart);
        }
        user.setCart(null);
        this.persist(user);

    }

    public void deleteCart(User user){
        Cart cart = user.getCart();
        if(cart != null){
            getCartService().deleteCart(cart);
        }
        user.setCart(null);
        this.persist(user);

    }

    public Boolean payCart(UserDTO userDTO) {
        User user = getById(userDTO.getId());
        Cart cart = user.getCart();

        if (getHelper().isToday(cart.getDate())) {
            this.checkVIP(user);
            cart.setPurchaseDate(new Date());
            cart.setPaid(true);
            getCartService().persist(cart);
            this.saveCartPaid(user);
            return true;
        }
        else {
            this.deleteCart(user);
            getCartService().deleteCart(cart);
            return false;
        }
    }

    public void setCart(User user, Cart cart){
        user.setCart(cart);
        this.persist(user);
    }

    private void checkVIP(User user){
        user.setUserVip(user.getCart().getTotal() > 10000 & getHelper().isSpecialMonth());
        this.persist(user);
    }

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
