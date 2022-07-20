package uz.pdp.appwarehouse.service;

import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.LoginDto;
import uz.pdp.appwarehouse.payload.RegisterDto;

public interface AuthService {
    ApiResponse register(RegisterDto dto);

    ApiResponse login(LoginDto dto);

    ApiResponse completedUser(Long id);
}
