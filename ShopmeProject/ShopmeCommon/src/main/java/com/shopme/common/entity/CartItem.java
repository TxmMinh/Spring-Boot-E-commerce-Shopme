package com.shopme.common.entity;

import com.shopme.common.entity.product.Product;

import javax.persistence.*;

@Entity
public class CartItem extends IdBasedEntity{
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private int quantity;

    public CartItem() {

    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItem [id=" + id + ", customer=" + customer.getFullName() + ", product=" + product.getShortName()
                + ", quantity=" + quantity + ']';
    }

    @Transient
    public float getSubtotal() {
        return product.getDiscountPrice() * quantity;
    }
}
