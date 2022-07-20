package uz.pdp.appwarehouse.service;

import uz.pdp.appwarehouse.domain.Input;
import uz.pdp.appwarehouse.domain.Users;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.InputDto;
import uz.pdp.appwarehouse.payload.InputProductDto;

import java.util.List;

public interface InputService {
    ApiResponse add(InputDto dto, Users users);

    List<Input> OneDayPeriod();

    List<InputProductDto> getExpresionDateProduct();
}
