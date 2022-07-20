package uz.pdp.appwarehouse.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appwarehouse.domain.template.AbsEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "shop")
public class Shop extends AbsEntity {

    @Column(nullable = false)
    private String name;

    @OneToOne(mappedBy = "shop", cascade = CascadeType.PERSIST)
    private Address address;
}
