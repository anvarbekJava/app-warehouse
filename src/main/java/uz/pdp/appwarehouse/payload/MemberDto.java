package uz.pdp.appwarehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appwarehouse.domain.Warehouse;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private Set<Warehouse> warehouses;

    private String role;
}
