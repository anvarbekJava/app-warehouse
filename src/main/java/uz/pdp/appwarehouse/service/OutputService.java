package uz.pdp.appwarehouse.service;

import uz.pdp.appwarehouse.domain.Output;
import uz.pdp.appwarehouse.domain.Users;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.OutputDto;

import java.util.List;

public interface OutputService {
    ApiResponse add(OutputDto dto, Users users);

    List<Output> getOneDaysPeriod();
}
