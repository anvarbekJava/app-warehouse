package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.appwarehouse.domain.Input;

import java.sql.Timestamp;
import java.util.List;

public interface InputRepository extends JpaRepository<Input, Long> {

    @Query(value = "select * from input\n" +
            "    join input_product ip on input.id = ip.input_id\n" +
            "    where date between  :start and :end ", nativeQuery = true)
    List<Input> getAllProductPeriod(@Param("start")Timestamp start, @Param("end") Timestamp end);


}
