package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.CategoryDto;
import uz.pdp.appwarehouse.service.CategoryService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/add")
    public HttpEntity<?> add(@Valid @RequestBody CategoryDto dto){
        ApiResponse apiResponse = categoryService.add(dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
    @PutMapping("/edet/{id}")
    public HttpEntity<?> edet(@PathVariable Long id, @Valid @RequestBody CategoryDto dto){
        ApiResponse apiResponse = categoryService.edet(id, dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
    @PutMapping("/enabled/{id}")
    public HttpEntity<?> enabled(@PathVariable Long id){
        ApiResponse apiResponse = categoryService.enabled(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
    @GetMapping("/get")
    public HttpEntity<?> getAll(){
        List<CategoryDto> categoryDtoList = categoryService.getAll();
        return ResponseEntity.ok(categoryDtoList);
    }
}
