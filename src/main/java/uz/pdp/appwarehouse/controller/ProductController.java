package uz.pdp.appwarehouse.controller;

import org.aspectj.apache.bcel.generic.LineNumberGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uz.pdp.appwarehouse.domain.Products;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.ProductDto;
import uz.pdp.appwarehouse.service.ProductService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public HttpEntity<?> add(@Valid@RequestBody ProductDto dto){
        ApiResponse apiResponse = productService.add(dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
    @PutMapping("/edet/{id}")
    public HttpEntity<?> edet(@Valid@RequestBody ProductDto dto, @PathVariable Long id){
        ApiResponse apiResponse = productService.edet(dto, id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @GetMapping("/get")
    public HttpEntity<?> get(){
        List<Products> products = productService.get();
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Products product : products) {
            ProductDto productDto = new ProductDto(
              product.getName(),
              product.getCategory().getName(),
              product.getMeasurement().getName(),
                    ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/download/")
                            .path(product.getPhoto().getId()).toUriString()
            );
            productDtoList.add(productDto);
        }
        return ResponseEntity.ok(productDtoList);
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable Long id){
        ApiResponse apiResponse = productService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PutMapping("/completed/{id}")
    public HttpEntity<?> completed(@PathVariable Long id){
        ApiResponse apiResponse = productService.completed(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @GetMapping("/get/{id}")
    public HttpEntity<?> getOne(@PathVariable Long id){
        Products product = productService.getOne(id);
        return ResponseEntity.ok(product);
    }
}
