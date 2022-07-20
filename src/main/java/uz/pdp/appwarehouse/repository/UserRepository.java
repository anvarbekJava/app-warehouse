package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouse.domain.Users;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

    boolean existsByPhoneNumber(String phoneNumber);
    Optional<Users> findByPhoneNumber(String phoneNumber);
}
