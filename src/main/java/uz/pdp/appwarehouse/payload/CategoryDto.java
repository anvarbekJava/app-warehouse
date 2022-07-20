package uz.pdp.appwarehouse.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CategoryDto {

    @NotNull
    private String name;

    private boolean enabled;

    private Long parentId;

    private String parentName;
}
