package uz.pdp.appwarehouse.service;

import uz.pdp.appwarehouse.domain.Users;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.ClientDto;

import java.util.List;

public interface ClientService {
    ApiResponse add(ClientDto dto);

    ApiResponse edet(ClientDto dto, Long id);

    ApiResponse delete(Long id);

    List<ClientDto> get();

    ApiResponse completed(Long id);

    ClientDto getOne(Long id);
}
