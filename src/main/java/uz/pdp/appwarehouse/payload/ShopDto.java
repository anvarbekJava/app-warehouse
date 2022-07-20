package uz.pdp.appwarehouse.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ShopDto {

    @NotNull(message = "{SHOP}")
    private String name;

    private AddressDto addressDto;
}
