package uz.pdp.appwarehouse.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CurrencyDto {

    @NotBlank(message = "{VALYUTA_NAME}")
    private String name;


    private boolean enabled;
}
