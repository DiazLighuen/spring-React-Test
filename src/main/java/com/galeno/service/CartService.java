package com.galeno.service;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphUtils;
import com.galeno.dto.AddToCartDTO;
import com.galeno.dto.DeleteFromCartDTO;
import com.galeno.dto.UserDTO;
import com.galeno.model.*;
import com.galeno.repository.CartRepository;
import com.galeno.utils.Helper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
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
    private Helper helper;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartRepository cartRepository;

    public void addToCart(AddToCartDTO addToCartDTO){
        User user = getUserService().getById(addToCartDTO.getUserId());
        Product product = getProductService().getById(addToCartDTO.getProductId());
        Cart cart = user.getCart();
        if(cart==null){
            cart = this.createNewCart();
            user.setCart(cart);
        }
        cart.addProduct(product);
        cart.calculateTotal(user);
        persist(cart);
    }

    public void deleteFromCart(DeleteFromCartDTO deleteFromCartDTO){
        User user = getUserService().getById(deleteFromCartDTO.getUserId());
        Product product = getProductService().getById(deleteFromCartDTO.getProductId());
        Cart cart = user.getCart();
        if(cart!=null) {
            cart.getProducts().remove(product);
            cart.calculateTotal(user);
            persist(cart);
        }
    }

    public void deleteAllItemsFromCart(UserDTO userDTO){
        User user = getUserService().getById(userDTO.getId());
        Cart cart = user.getCart();
        if(cart!=null) {
            cart.getProducts().clear();
            cart.calculateTotal(user);
            persist(cart);
        }
    }

    public Boolean payCart(UserDTO userDTO){
        Cart cart = getUserService().getById(userDTO.getId()).getCart();
        cart.setPaid(true);
        this.persist(cart);
        return true;
    }

    public Cart createNewCart(){
        Cart cart;
        if(helper.isPromotionalDate()){
            cart = new PromoCart();
        }
        else {
            cart = new SimpleCart();
        }
        return cart;
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
