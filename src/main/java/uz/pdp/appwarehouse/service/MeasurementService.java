package uz.pdp.appwarehouse.service;

import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.MeasurementDto;

public interface MeasurementService {
    ApiResponse add(MeasurementDto dto);

    ApiResponse enabled(Long id);
}
