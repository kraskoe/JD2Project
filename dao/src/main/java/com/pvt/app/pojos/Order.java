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
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ORDER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    @Column(name = "ORDER_RECEIVED", updatable = false)
    private LocalDateTime creationTime;

    @Column(name = "ORDER_CONFIRMED", insertable = false)
    private LocalDateTime confirmationTime;

    @Column(name = "ORDER_DELIVERED", insertable = false)
    private LocalDateTime deliveryTime;

    @Column(name="STATUS", length=10, nullable=false)
    private String stat = OrderStatus.RECEIVED.getStatus();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "ORDERS_PRODUCTS",
            joinColumns = @JoinColumn(name = "ORDER_ID"))
    private List<Product> products = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    public Order(User user){
        this.user = user;
    }
}
