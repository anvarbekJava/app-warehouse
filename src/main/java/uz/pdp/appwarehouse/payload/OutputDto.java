package uz.pdp.appwarehouse.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Data
public class OutputDto {
    @NotNull(message = "{DATE}")
    private Long date;

    @NotNull(message = "{WAREHOUSE_ID}")
    private Long warehouseId;

    @NotNull(message = "{Valyuta tanlanmagan}")
    private Long currencyId;

    @NotNull(message = "{FACTURA_NUMBER}")
    private String facturaNumber;

    @NotNull(message = "{clientId}")
    private Long clientId;

    private List<OutputProductDto> productDtoList;
}
