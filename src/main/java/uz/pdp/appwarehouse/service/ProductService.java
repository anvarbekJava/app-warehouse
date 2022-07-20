package uz.pdp.appwarehouse.service;

import uz.pdp.appwarehouse.domain.Products;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.ProductDto;

import java.util.List;

public interface ProductService {
    ApiResponse add(ProductDto dto);

    ApiResponse edet(ProductDto dto, Long id);


    List<Products> get();

    ApiResponse delete(Long id);

    ApiResponse completed(Long id);

    Products getOne(Long id);
}
