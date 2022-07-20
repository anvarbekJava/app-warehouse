package uz.pdp.appwarehouse.payload;

import lombok.Data;
import uz.pdp.appwarehouse.domain.enums.Permission;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class RoleDto {
    @NotBlank(message = "{ROLE_NAME}")
    private String name;

    @NotEmpty
    private List<Permission> permissions;


    private String description;
}
