package uz.pdp.appwarehouse.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import uz.pdp.appwarehouse.domain.Currency;
import uz.pdp.appwarehouse.domain.Supplier;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
public class InputDto {

    @NotNull(message = "{DATE}")
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    private Long date;

    @NotNull(message = "{WAREHOUSE_ID}")
    private Long warehouseId;

    @NotNull(message = "{SUPPLIER}")
    private Long supplierId;

    @NotNull(message = "{Valyuta tanlanmagan}")
    private Long currencyId;

    @NotNull(message = "{FACTURA_NUMBER}")
    private String facturaNumber;

    private List<InputProductDto> productDtoList;


    private Long productId;

}
