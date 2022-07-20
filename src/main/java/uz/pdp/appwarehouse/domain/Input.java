package uz.pdp.appwarehouse.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Input {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Timestamp date;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Warehouse warehouse;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Supplier supplier;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Currency currency;

    @ManyToOne(optional = false)
    private Users users;

    @Column(nullable = false)
    private String facturaNumber;

    @Column(nullable = false, unique = true)
    private Integer code = (int)(Math.random()*(99999-10000)+10000);

    @OneToMany(mappedBy = "input", cascade = CascadeType.PERSIST)
    private List<InputProduct> inputProduct;

    public Input(Timestamp date, Warehouse warehouse, Supplier supplier, Currency currency, String facturaNumber, Users users) {
        this.date = date;
        this.warehouse = warehouse;
        this.supplier = supplier;
        this.currency = currency;
        this.facturaNumber = facturaNumber;
        this.users = users;
    }
}
