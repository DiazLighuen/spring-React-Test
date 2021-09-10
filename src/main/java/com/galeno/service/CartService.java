package com.galeno.service;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphUtils;
import com.galeno.dto.*;
import com.galeno.model.*;
import com.galeno.repository.CartRepository;
import com.galeno.utils.Helper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@Getter
public class CartService {

    @Autowired
    private Helper helper;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartRepository cartRepository;

    public void addToCart(AddToCartDTO addToCartDTO) {
        User user = getUserService().getById(addToCartDTO.getUserId());
        Product product = getProductService().getById(addToCartDTO.getProductId());
        Cart cart = user.getCart();
        if (cart == null) {
            cart = this.createNewCart(user);
            getUserService().setCart(user,cart);
            cart = user.getCart();
        }
        if (getHelper().isToday(cart.getDate())) {
            cart.addProduct(product);
            cart.calculateTotal(user);
            persist(cart);
        }
        else {
            getUserService().deleteCart(user);
            this.deleteCart(cart);
        }
    }

    public void deleteFromCart(DeleteFromCartDTO deleteFromCartDTO) {
        User user = getUserService().getById(deleteFromCartDTO.getUserId());
        Product product = getProductService().getById(deleteFromCartDTO.getProductId());
        Cart cart = user.getCart();
        if (cart != null) {
            if (getHelper().isToday(cart.getDate())) {
                cart.getProducts().remove(product);
                cart.calculateTotal(user);
                persist(cart);
            }
            else {
                getUserService().deleteCart(user);
                this.deleteCart(cart);
            }
        }
    }

    public void deleteAllItemsFromCart(CartListDTO cartListDTO) {
        Cart cart = this.getById(cartListDTO.getId());
        if (cart != null) {
            if (getHelper().isToday(cart.getDate())) {
                cart.getProducts().clear();
                persist(cart);
            }
        }
    }

    public Cart createNewCart(User user) {
        Cart cart;
        if (helper.isPromotionalDate()) {
            cart = new PromoCart(user);
        } else {
            cart = new SimpleCart(user);
        }
        return cart;
    }

    public void deleteCart(Cart cart) {
        cartRepository.delete(cart);
    }

    @Transactional
    public void deleteCart(CartListDTO cartListDTO){
        Cart cart = this.getById(cartListDTO.getId());
        this.deleteAllItemsFromCart(cartListDTO);
        getUserService().deleteCart(cart.getUser());
        cartRepository.delete(cart);
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
    public Cart getById(Long id, String... with) {
        return findById(id, with).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Optional<Cart> findById(Long id, String... with) {
        return getCartRepository().findById(id, generateEntityGraph(with));
    }
}
