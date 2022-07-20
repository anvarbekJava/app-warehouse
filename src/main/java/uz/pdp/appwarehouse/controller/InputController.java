package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.component.CurrentUser;
import uz.pdp.appwarehouse.domain.Users;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.InputDto;
import uz.pdp.appwarehouse.service.InputService;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/input")
public class InputController {
    @Autowired
    InputService inputService;

    @PostMapping("/add")
    public HttpEntity<?> add(@Valid @RequestBody InputDto dto, @CurrentUser Users users){
        ApiResponse apiResponse = inputService.add(dto, users);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
    @GetMapping
    public String get(){
        return "Salom";
    }
}
