package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouse.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    boolean existsByName(String name);
}
