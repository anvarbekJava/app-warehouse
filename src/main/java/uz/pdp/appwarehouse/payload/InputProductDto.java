package uz.pdp.appwarehouse.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

@Data
public class InputProductDto {

    @NotNull(message = "{AMOUNT}")
    private Double amount;

    @NotNull(message = "{PRICE}")
    private Double price;

    private Long expireDate;

    private Date expireDateOwn;  //12-03-2022 04:09:32

    private Long productId;

    private String productName;

    private String productUrl;

    public InputProductDto(Double amount, Double price, Long expireDate, String productName, String productUrl) {
        this.amount = amount;
        this.price = price;
        this.expireDate = expireDate;
        this.productName = productName;
        this.productUrl = productUrl;
    }
}
