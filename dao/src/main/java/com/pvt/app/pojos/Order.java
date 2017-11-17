package com.pvt.app.pojos;

import com.pvt.app.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yauheni Krasko on 17.11.2017.
 */

@Data
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @Column(name = "ORDER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime creationTime;

    @Column(insertable = false)
    private LocalDateTime confirmationTime;

    @Column(insertable = false)
    private LocalDateTime deliveryTime;

    @Column(name="STATUS", length=10, unique=true, nullable=false)
    private String status = OrderStatus.RECEIVED.getStatus();

    @OneToMany(mappedBy = "order")
    private List<Product> products = new ArrayList<>();
}
