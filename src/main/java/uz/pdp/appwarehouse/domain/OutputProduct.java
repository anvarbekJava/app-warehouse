package uz.pdp.appwarehouse.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
public class OutputProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Products product;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Double price;

    @JsonIgnore
    @JoinColumn(name = "output")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Output output;

    @Transient
    private Double summa;

    public OutputProduct() {

    }

    public Double getSumma() {
        return this.amount*this.price;
    }

    public OutputProduct(Products product, Double amount, Double price, Output output) {
        this.product = product;
        this.amount = amount;
        this.price = price;
        this.output = output;
    }
}
