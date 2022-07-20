package uz.pdp.appwarehouse.service;

import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.WarehouseDto;

import java.util.List;

public interface WarehouseService {
    
    ApiResponse add(WarehouseDto dto);

    ApiResponse edet(WarehouseDto dto, Long id);

    List<WarehouseDto> getAll();

    ApiResponse completed(Long id);
}
