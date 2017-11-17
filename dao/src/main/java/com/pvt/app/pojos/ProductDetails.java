package com.pvt.app.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

/**
 * Created by Yauheni Krasko on 17.11.2017.
 */

@Data
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "PRODUCT_DETAILS")
public class ProductDetails {
    @Id
    @Column(name = "PRODUCT_ID", unique = true, nullable = false)
    @GeneratedValue(generator = "oto")
    @GenericGenerator(name = "oto",
            strategy = "foreign",
            parameters = @Parameter(name = "property", value = "product"))
    private Integer id;

    @Column(name = "DETAILS")
    private String detail;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private Product product;
}
