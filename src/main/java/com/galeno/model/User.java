package com.galeno.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Long id;
    private String username;
    private String password;
    private Boolean userVip;
    @OneToOne(cascade=CascadeType.ALL)
    private Cart cart;
    @OneToMany(cascade=CascadeType.ALL)
    private List<Cart> carts = new ArrayList<>();

    public User(Long id, String username, String password, Boolean userVip, Cart cart, List<Cart> carts) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userVip = userVip;
        this.cart = cart;
        this.carts = carts;
    }
}
