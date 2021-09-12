package com.galeno.test.service;

import com.galeno.MainApp;
import com.galeno.dto.UserDTO;
import com.galeno.model.Cart;
import com.galeno.model.PromoCart;
import com.galeno.model.User;
import com.galeno.repository.UserRepository;
import com.galeno.service.CartService;
import com.galeno.service.UserService;
import com.galeno.utils.Helper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = MainApp.class)
//public class UserServiceTest {
//
//    @MockBean
//    private UserRepository userRepository;
//
//    @MockBean
//    private CartService cartService;
//
//    @MockBean
//    private Helper helper;
//
//    @InjectMocks
//    private UserService userService;
//
//    @Autowired
//    private ModelMapper modelMapper;
//
//
//    @Test
//    public void testToggleVIP(){
//        Long id = Long.valueOf(2);
//        String username = "TUNPQkFDQTQyMTIw";
//        String password = "wadJwadHghJAWDUJ";
//        Boolean userVip = false;
//        Cart cart = new PromoCart();
//        List<Cart> carts = new ArrayList<>();
//        User user = new User(id,username,password,userVip,cart,carts);
//        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
//        userService.toggleVIP(userDTO);
//        assertEquals(id, user.getId());
//        assertNotEquals(userVip, user.getUserVip());
//
//    }
//}
