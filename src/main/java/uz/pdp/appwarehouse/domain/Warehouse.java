package uz.pdp.appwarehouse.domain;

import lombok.*;
import uz.pdp.appwarehouse.domain.template.AbsEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "warehouse")
public class Warehouse extends AbsEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private Shop shop;

    @OneToOne(mappedBy = "warehouse", cascade = CascadeType.PERSIST)
    private Address address;
}
