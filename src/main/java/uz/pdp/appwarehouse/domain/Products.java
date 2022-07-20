package uz.pdp.appwarehouse.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appwarehouse.domain.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "product")
public class Products extends AbsEntity {
    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private Category category;

    @Column(nullable = false, unique = true)
    private Integer code = (int)(Math.random()*(9999-1000)+1000);

    @OneToOne
    private Attachment photo;

    @ManyToOne(optional = false)
    private Measurement measurement;

    public Products(String name, Category category, Attachment photo, Measurement measurement) {
        this.name = name;
        this.category = category;
        this.photo = photo;
        this.measurement = measurement;
    }
}
