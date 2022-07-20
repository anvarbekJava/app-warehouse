package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouse.domain.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    boolean existsByName(String name);
}
