package uz.pdp.appwarehouse.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class WarehouseDto {
    @NotNull(message = "{NAME}")
    private String name;

    private boolean enabled;

    private Long shopId;

    private AddressDto addressDto;

    private String shopName;

    private String city;
}
