package uz.pdp.appwarehouse.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OutputProductDto {

    @NotNull(message = "{AMOUNT}")
    private Double amount;

    @NotNull(message = "{PRICE}")
    private Double price;


    @NotNull(message = "{PRODUCT_ID}")
    private Long productId;

    private String productName;

    private String productUrl;

    public OutputProductDto(Double amount, Double price,  String productName, String productUrl) {
        this.amount = amount;
        this.price = price;
        this.productName = productName;
        this.productUrl = productUrl;
    }
}
