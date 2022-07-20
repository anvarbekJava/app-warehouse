package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.domain.Warehouse;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.WarehouseDto;
import uz.pdp.appwarehouse.service.WarehouseService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {
    @Autowired
    WarehouseService warehouseService;

    @PostMapping("/add")
    public HttpEntity<?> add(@Valid @RequestBody WarehouseDto dto){
        ApiResponse apiResponse = warehouseService.add(dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PutMapping("/edet/{id}")
    public HttpEntity<?> edet(@Valid @RequestBody WarehouseDto dto, @PathVariable Long id){
        ApiResponse apiResponse = warehouseService.edet(dto, id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @GetMapping("/get")
    public HttpEntity<?> getAll(){
        List<WarehouseDto> warehouseDtoList = warehouseService.getAll();
        return ResponseEntity.ok(warehouseDtoList);
    }

    @PutMapping("/completed/{id}")
    public HttpEntity<?> completed(@PathVariable Long id){
        ApiResponse apiResponse = warehouseService.completed(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
