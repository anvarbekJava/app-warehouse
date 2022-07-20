package uz.pdp.appwarehouse.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private String street;

    private String homeNumber;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private Warehouse warehouse;

    public Address(String city, String district, String street, String homeNumber, Shop shop, Warehouse warehouse) {
        this.city = city;
        this.district = district;
        this.street = street;
        this.homeNumber = homeNumber;
        this.shop = shop;
        this.warehouse = warehouse;
    }
}
