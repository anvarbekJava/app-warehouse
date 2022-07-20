package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouse.domain.Output;

import java.sql.Timestamp;
import java.util.List;

public interface OutputRepository extends JpaRepository<Output, Long> {
    List<Output> findByDateBetween(Timestamp start, Timestamp end);
}
