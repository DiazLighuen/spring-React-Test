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
public abstract class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Long id;
    private Date date;
    @ManyToMany
    private Set<Product> products;
    private Boolean paid;
    private Double total;

    public Cart(){
        this.date = new Date();
        this.products = new HashSet<Product>();
        this.paid = false;
        this.total = 0.0;
    }

    public Cart(Product product) {
        this.date = new Date();
        this.products = new HashSet<Product>();
        this.products.add(product);
        this.paid = false;
        this.total = 0.0;
    }

    public void addProduct(Product product){
        this.products.add(product);
    }

    public void delProduct(Product product){
        this.products.remove(product);
    }

    public abstract void calculateTotal(User user);
}
