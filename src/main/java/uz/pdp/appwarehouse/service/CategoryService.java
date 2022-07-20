package uz.pdp.appwarehouse.service;

import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.CategoryDto;

import java.util.List;

public interface CategoryService {
    ApiResponse add(CategoryDto dto);

    ApiResponse edet(Long id, CategoryDto dto);

    ApiResponse enabled(Long id);

    List<CategoryDto> getAll();
}
