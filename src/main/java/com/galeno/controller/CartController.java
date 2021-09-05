package com.galeno.controller;

import com.galeno.dto.CartDTO;

import com.galeno.dto.ProductListingDTO;
import com.galeno.model.Cart;
import com.galeno.service.CartService;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cart")
@Getter
public class CartController {

    @Autowired
    private CartService cartService;
    private final ModelMapper modelMapper;

    public CartController(ModelMapper mapper){
        this.modelMapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<CartDTO>> read(){
        return okResponse(getCartService().findAll());
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

    private ResponseEntity<Page<ProductListingDTO>> okListingResponse(Page<Cart> src){
        return ResponseEntity.ok(src.map(this::toListingDTO));
    }

    private CartDTO toDTO(Cart src){
        return getModelMapper().map(src, CartDTO.class);
    }

    private ProductListingDTO toListingDTO(Cart src) {
        return getModelMapper().map(src, ProductListingDTO.class);
    }
}
