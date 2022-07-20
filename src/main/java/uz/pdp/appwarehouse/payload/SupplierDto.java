package uz.pdp.appwarehouse.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class SupplierDto {

    @NotNull(message = "{FULLNAME")
    private String fullName;

    @NotBlank(message = "{PHONE_NUMBER}")
    @Pattern(regexp = "^[+][0-9]{9,15}$", message = "{PHONE_NUMBER_PATTERN}")
    private String phoneNumber;

    @NotNull(message = "{SHOP_ID}")
    private Long shopId;

    private boolean enabled;

    private String shopName;
}
