package uz.pdp.appwarehouse.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MeasurementDto {
    @NotNull(message = "{NAME}")
    private String name;

    private boolean enabled;
}
