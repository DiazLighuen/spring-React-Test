package com.galeno.service;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphUtils;
import com.galeno.dto.AddToCartDTO;
import com.galeno.model.Cart;
import com.galeno.model.Product;
import com.galeno.model.User;
import com.galeno.repository.CartRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Getter
public class CartService {

    @Autowired
    private UserService userService;

    @Autowired
    private CartRepository cartRepository;

    public void addToCart(Product product, User user){
        if(user.getActiveCart() == null){
            Cart cart = new Cart(product);
            cartRepository.save(cart);
            user.setActiveCart(cart);
            userService.persist(user);
        }
        else {
            Cart cart = user.getActiveCart();
            cart.addProduct(product);
            cartRepository.save(cart);
        }
    }

    public EntityGraph generateEntityGraph(String... paths) {
        return paths == null || paths.length == 0 ? null : EntityGraphUtils.fromAttributePaths(paths);
    }

    @Transactional(readOnly = true)
    public List<Cart> findAll(String... with) {
        return new ArrayList<>((Collection<? extends Cart>) getCartRepository().findAll(generateEntityGraph(with)));
    }

    @Transactional
    public Cart persist(Cart cart) {
        return getCartRepository().save(cart);
    }

    @Transactional
    public Cart getById(Long id, String... with){
        return findById(id, with).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Optional<Cart> findById(Long id, String... with) {
        return getCartRepository().findById(id,generateEntityGraph(with));
    }
}
