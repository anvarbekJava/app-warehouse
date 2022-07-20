package uz.pdp.appwarehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.domain.Measurement;
import uz.pdp.appwarehouse.exseption.ResourceNotFoundException;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.MeasurementDto;
import uz.pdp.appwarehouse.repository.MeasurementRepository;
import uz.pdp.appwarehouse.service.MeasurementService;

@Service
public class MeasurementServiceImpl implements MeasurementService {
    @Autowired
    MeasurementRepository measurementRepository;
    @Override
    public ApiResponse add(MeasurementDto dto) {
        if (measurementRepository.existsByName(dto.getName()))
            return new ApiResponse("Measurement mavjud", false);
        Measurement measurement = new Measurement(dto.getName());
        measurementRepository.save(measurement);
        return new ApiResponse("Saqlandi", true);
    }
    @Override
    public ApiResponse enabled(Long id) {
        Measurement measurement = measurementRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Measurement", "enabled", false));
        if (measurement.isEnabled()){
            measurement.setEnabled(false);
        }else {
            measurement.setEnabled(true);
        }
        measurementRepository.save(measurement);
        return new ApiResponse("Ozgartildi", true);
    }
}
