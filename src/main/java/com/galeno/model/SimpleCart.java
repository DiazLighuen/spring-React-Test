package com.galeno.model;

import javax.persistence.Entity;

@Entity
public class SimpleCart extends Cart{
    public SimpleCart() {
        super();
    }

    public SimpleCart(User user) {
        super(user);
    }

    public void calculateTotal(User user) {
        int size = this.getProducts().size();
        double total = this.getProducts().stream().map(Product::getPrice).reduce(0.0,Double::sum);

        if(size == 4){
            total = total*0.75;
        }

        if(size >= 10){
            if(user.getUserVip() & total > 4000.0){
                total =total-2000.0;
            }
            else {
                    total = total - 100.0;
            }
        }
        this.setTotal(total);
    }
}
