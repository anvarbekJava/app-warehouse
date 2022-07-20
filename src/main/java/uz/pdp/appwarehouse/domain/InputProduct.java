package uz.pdp.appwarehouse.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InputProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Products product;

    @Column(nullable = false)
    private Double amount;

    private Double price;

    @Column(nullable = false)
    private Timestamp expireDate;

    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Input input;

    @Transient
    private Double summa;

    public Double getSumma() {
        return this.amount*this.price;
    }

    public InputProduct(Products product, Double amount, Double price, Timestamp expireDate, Input input) {
        this.product = product;
        this.amount = amount;
        this.price = price;
        this.expireDate = expireDate;
        this.input = input;
    }
}
