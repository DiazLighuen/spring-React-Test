package com.galeno.controller;

import com.galeno.dto.*;
import com.galeno.model.User;
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
@RequestMapping("user")
@Getter
public class UserController {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    private final ModelMapper modelMapper;

    public UserController(ModelMapper mapper){
        this.modelMapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> read(){
        return okResponse(getUserService().findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> read(@PathVariable(name = "id") Long id){
        return okResponse(getUserService().getById(id));
    }

    @PostMapping("/toggleVIP")
    public ResponseEntity toggleVIP(@RequestBody UserDTO userDTO){
        getUserService().toggleVIP(userDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/payCart")
    public ResponseEntity payCart(@RequestBody UserDTO userDTO){
        HttpStatus response = getUserService().payCart(userDTO)?HttpStatus.OK:HttpStatus.CONFLICT;
        return new ResponseEntity(response);
    }

    private ResponseEntity<List<UserDTO>> okResponse(List<User> src){
        return ResponseEntity.ok(src.stream().map(this::toDTO).collect(Collectors.toList()));
    }

    private ResponseEntity<UserDTO> okResponse(User src) {
        return ResponseEntity.ok(this.toDTO(src));
    }

    private ResponseEntity<Page<UserDTO>> okListingResponse(Page<User> src){
        return ResponseEntity.ok(src.map(this::toListingDTO));
    }

    private UserDTO toDTO(User src){
        return getModelMapper().map(src, UserDTO.class);
    }

    private UserDTO toListingDTO(User src) {
        return getModelMapper().map(src, UserDTO.class);
    }
}
