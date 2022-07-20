package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.LoginDto;
import uz.pdp.appwarehouse.payload.RegisterDto;
import uz.pdp.appwarehouse.service.AuthService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public HttpEntity<?> register(@Valid @RequestBody RegisterDto dto){
        ApiResponse apiResponse = authService.register(dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PostMapping("/login")
    public HttpEntity<?> login(@Valid @RequestBody LoginDto dto){
        ApiResponse apiResponse = authService.login(dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PutMapping("/completed/{id}")
    public HttpEntity<?> completed(@PathVariable Long id){
       ApiResponse apiResponse =  authService.completedUser(id);
       return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
