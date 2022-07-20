package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouse.domain.InputProduct;

public interface InputProductRepository extends JpaRepository<InputProduct, Long> {
}
