package uz.pdp.appwarehouse.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProductDto {
    @NotNull(message = "{NAME_PRODUCT}")
    private String name;

    @NotNull(message = "{CATEGORY_ID}")
    private Long categoryId;

    @NotNull(message = "{ATTACHMENT}")
    private String attachmentId;

    @NotNull(message = "{MEASUREMENT}")
    private Long measurementId;

    private String categoryName;

    private String measurementName;

    private String downloadUrl;

    public ProductDto(String name, String categoryName, String measurementName, String downloadUrl) {
        this.name = name;
        this.categoryName = categoryName;
        this.measurementName = measurementName;
        this.downloadUrl = downloadUrl;
    }
}
