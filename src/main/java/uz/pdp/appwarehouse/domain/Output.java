package uz.pdp.appwarehouse.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Output {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Timestamp date;

    @ManyToOne(optional = false)
    private Warehouse warehouse;

    @ManyToOne(optional = false)
    private Currency currency;

    @Column(nullable = false)
    private String facturaNumber;

    @Column(nullable = false, unique = true)
    private Integer code = (int)(Math.random()*(99999-10000)+10000);

    @ManyToOne(optional = false)
    private Client client;

    @ManyToOne(optional = false)
    private Users users;

    @OneToMany(mappedBy = "output", cascade = CascadeType.PERSIST)
    private List<OutputProduct> outputProduct;

    public Output(Timestamp date, Warehouse warehouse, Currency currency, String facturaNumber, Client client, Users users) {
        this.date = date;
        this.warehouse = warehouse;
        this.currency = currency;
        this.facturaNumber = facturaNumber;
        this.client = client;
        this.users = users;
    }
}
