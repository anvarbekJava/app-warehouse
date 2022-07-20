package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouse.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
