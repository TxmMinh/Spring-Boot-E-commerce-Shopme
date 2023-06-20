package com.shopme.common.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "product_images")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public ProductImage() {

    }

    public ProductImage(String name, Product product) {
        this.name = name;
        this.product = product;
    }

}