package com.galeno.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
    @Column(nullable = false, unique = true)
    private String username;
    private String password;
    private Boolean userVip;
    private List<Cart> carts;
}
