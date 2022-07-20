package uz.pdp.appwarehouse.service;

import uz.pdp.appwarehouse.domain.Supplier;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.SupplierDto;

import java.util.List;

public interface SupplierService {
    ApiResponse add(SupplierDto dto);

    List<SupplierDto> get();

    ApiResponse completed(Long id);

    ApiResponse edet(SupplierDto dto, Long id);

    Supplier getOne(Long id);

}
