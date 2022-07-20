package uz.pdp.appwarehouse.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class AddressDto {

    @NotNull(message = "{CITY}")
    private String city;

    @NotNull(message = "{DISTRICT}")
    private String district;

    @NotNull(message = "{STREET}")
    private String street;

    private String homeNumber;
}
