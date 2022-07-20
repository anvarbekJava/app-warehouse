package uz.pdp.appwarehouse.payload;

import lombok.Data;
import uz.pdp.appwarehouse.domain.Warehouse;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class RegisterDto {
    @NotNull(message = "{FIRST_NAME")
    private String firstName;

    @NotNull(message = "{LAST_NAME")
    private String lastName;

    @NotBlank(message = "{PHONE_NUMBER}")
    @Pattern(regexp = "^[+][0-9]{9,15}$", message = "{PHONE_NUMBER_PATTERN}")
    private String phoneNumber;

    @NotNull(message = "{PASSWORD}")
    @Size(min = 8, max = 12, message = "{PASSOWR_LENGTH}")
    private String password;

    @NotNull(message = "{WAREHOUSE")
    private List<Long> warehouses;

    @NotNull(message = "{ROLE_USER}")
    private Long roleId;


}
