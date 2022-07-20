package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.domain.Supplier;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.SupplierDto;
import uz.pdp.appwarehouse.service.SupplierService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    @PostMapping("/add")
    public HttpEntity<?> add(@Valid @RequestBody SupplierDto dto){
        ApiResponse apiResponse = supplierService.add(dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @GetMapping("/get")
    public HttpEntity<?> get(){
        List<SupplierDto> supplierDtoList = supplierService.get();
        return ResponseEntity.ok(supplierDtoList);
    }

    @PutMapping("/completed/{id}")
    public HttpEntity<?> completed(@PathVariable Long id){
        ApiResponse apiResponse = supplierService.completed(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PutMapping("/edet/{id}")
    public HttpEntity<?> edet(@Valid @RequestBody SupplierDto dto, @PathVariable Long id){
        ApiResponse apiResponse = supplierService.edet(dto, id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @GetMapping("/get/{id}")
    public HttpEntity<?> getOne(@PathVariable Long id){
        Supplier supplier = supplierService.getOne(id);
        return ResponseEntity.ok(supplier);
    }
}
