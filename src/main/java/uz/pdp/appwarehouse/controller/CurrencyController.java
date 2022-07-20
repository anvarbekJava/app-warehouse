package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.CurrencyDto;
import uz.pdp.appwarehouse.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController {
    @Autowired
    CurrencyService currencyService;

    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody CurrencyDto dto){
        ApiResponse apiResponse= currencyService.add(dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PutMapping("/completed/{id}")
    public HttpEntity<?> completed(@PathVariable Long id){
        ApiResponse apiResponse = currencyService.completed(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @GetMapping("/get")
    public HttpEntity<?> get(){
        List<CurrencyDto> dtoList = currencyService.get();
        return ResponseEntity.ok(dtoList);
    }
}
