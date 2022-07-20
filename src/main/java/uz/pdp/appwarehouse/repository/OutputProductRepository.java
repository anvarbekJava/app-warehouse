package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouse.domain.OutputProduct;

public interface OutputProductRepository extends JpaRepository<OutputProduct, Long> {
}
