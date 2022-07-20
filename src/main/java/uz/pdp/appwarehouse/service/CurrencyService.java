package uz.pdp.appwarehouse.service;

import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.CurrencyDto;

import java.util.List;

public interface CurrencyService {
    ApiResponse add(CurrencyDto dto);

    ApiResponse completed(Long id);

    List<CurrencyDto> get();
}
