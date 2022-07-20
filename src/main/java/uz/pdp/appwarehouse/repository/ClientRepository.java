package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouse.domain.Client;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    boolean existsByPhoneNumber(String phoneNumber);

    List<Client> findByShopId(Long shop_id);
}
