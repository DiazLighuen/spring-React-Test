package com.galeno.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

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
    @ManyToMany(cascade=CascadeType.ALL)
    private List<Product> products;
    private Boolean paid;
    private Double total;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Cart() {
    }

    public Cart(User user){
        this.date = new Date();
        this.products = new ArrayList<Product>();
        this.paid = false;
        this.total = 0.0;
        this.user = user;
    }

    public void addProduct(Product product){
        this.products.add(product);
    }

    public void delProduct(Product product){
        this.products.remove(product);
    }

    public abstract void calculateTotal(User user);
}
