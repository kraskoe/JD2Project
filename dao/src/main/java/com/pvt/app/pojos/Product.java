package com.pvt.app.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Yauheni Krasko on 17.11.2017.
 */

@Data
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "PRODUCTS")
public class Product {
    private static final long serialVersionUID = 1l;
    @Id
    @Column(name = "PRODUCT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private Double price;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private ProductDetails details;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;
}
