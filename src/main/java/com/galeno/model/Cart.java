package com.galeno.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Long id;
    private Date date;
    @ManyToMany
    private Set<Product> products = new HashSet<>();
    private Boolean paid = false;

    public Cart(){

    }

    public Cart(Product product) {
        this.products.add(product);
        this.date = new Date();
    }

    public void addProduct(Product product){
        this.products.add(product);
    }
}
