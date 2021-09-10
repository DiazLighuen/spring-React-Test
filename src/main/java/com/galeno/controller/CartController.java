package com.galeno.controller;

import com.galeno.dto.*;

import com.galeno.model.Cart;
import com.galeno.service.CartService;
import com.galeno.service.ProductService;
import com.galeno.service.UserService;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cart")
@Getter
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    private final ModelMapper modelMapper;

    public CartController(ModelMapper mapper){
        this.modelMapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<CartDTO>> read(){
        return okResponse(getCartService().findAll());
    }

    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody AddToCartDTO addToCartDTO){
        getCartService().addToCart(addToCartDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/deleteFromCart")
    public ResponseEntity deleteFromCart(@RequestBody DeleteFromCartDTO deleteFromCartDTO){
        getCartService().deleteFromCart(deleteFromCartDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/deleteCart")
    public ResponseEntity deleteCart(@RequestBody CartListDTO cartListDTO) {
        getCartService().deleteCart(cartListDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/deleteAll")
    public ResponseEntity deleteAll(@RequestBody CartListDTO cartListDTO){
        getCartService().deleteAllItemsFromCart(cartListDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDTO> read(@PathVariable(name = "id") Long id){
        return okResponse(getCartService().getById(id));
    }


    private ResponseEntity<List<CartDTO>> okResponse(List<Cart> src){
        return ResponseEntity.ok(src.stream().map(this::toDTO).collect(Collectors.toList()));
    }

    private ResponseEntity<CartDTO> okResponse(Cart src) {
        return ResponseEntity.ok(this.toDTO(src));
    }

    private ResponseEntity<Page<CartDTO>> okListingResponse(Page<Cart> src){
        return ResponseEntity.ok(src.map(this::toListingDTO));
    }

    private CartDTO toDTO(Cart src){
        return getModelMapper().map(src, CartDTO.class);
    }

    private CartDTO toListingDTO(Cart src) {
        return getModelMapper().map(src, CartDTO.class);
    }
}
