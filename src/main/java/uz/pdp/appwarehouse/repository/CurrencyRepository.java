package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouse.domain.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    boolean existsByName(String name);
}
