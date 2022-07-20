package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.MeasurementDto;
import uz.pdp.appwarehouse.service.MeasurementService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/measurement")
public class MeasurementController {
    @Autowired
    MeasurementService measurementService;

    @PostMapping("/add")
    public HttpEntity<?> add(@Valid @RequestBody MeasurementDto dto){
        ApiResponse apiResponse = measurementService.add(dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
    @PutMapping("/enabled/{id}")
    public HttpEntity<?> enabled(@PathVariable Long id){
        ApiResponse apiResponse  = measurementService.enabled(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
