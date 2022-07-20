package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.ShopDto;
import uz.pdp.appwarehouse.service.ShopService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/shop")
public class ShopController {
    @Autowired
    ShopService shopService;

    @PostMapping("/add")
    public HttpEntity<?> add(@Valid @RequestBody ShopDto dto){
        ApiResponse apiResponse = shopService.add(dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
