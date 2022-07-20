package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouse.domain.Products;


public interface ProductRepository extends JpaRepository<Products, Long> {

    boolean existsByName(String name);
}
