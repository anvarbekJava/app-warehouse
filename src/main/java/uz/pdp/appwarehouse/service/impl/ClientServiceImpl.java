package uz.pdp.appwarehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.domain.Client;
import uz.pdp.appwarehouse.domain.Shop;
import uz.pdp.appwarehouse.domain.Users;
import uz.pdp.appwarehouse.exseption.ResourceNotFoundException;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.ClientDto;
import uz.pdp.appwarehouse.repository.ClientRepository;
import uz.pdp.appwarehouse.repository.ShopRepository;
import uz.pdp.appwarehouse.service.ClientService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ShopRepository shopRepository;

    @Override
    public ApiResponse add(ClientDto dto) {
       if (clientRepository.existsByPhoneNumber(dto.getPhoneNumber()))
           return new ApiResponse("Bunday client mavjud", false);
        Shop shop = shopRepository.findById(dto.getShopId()).orElseThrow(() -> new ResourceNotFoundException("CLient", "shop", true));
        Client client = new Client(
                dto.getFullName(),
                dto.getPhoneNumber(),
                shop
        );
        clientRepository.save(client);
        return new ApiResponse("Saqlandi", true);
    }

    @Override
    public ApiResponse edet(ClientDto dto, Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client", "client", true));
        Shop shop = shopRepository.findById(dto.getShopId()).orElseThrow(() -> new ResourceNotFoundException("CLient", "shop", true));

        client.setFullName(dto.getFullName());
        client.setPhoneNumber(dto.getPhoneNumber());
        client.setShop(shop);

        clientRepository.save(client);

        return new ApiResponse("Saqlandi", true);
    }

    @Override
    public ApiResponse delete(Long id) {
        try {
            clientRepository.deleteById(id);
            return new ApiResponse("Deleted client", true);
        }catch (Exception e){
            return new ApiResponse("No deleted client", false);
        }
    }

    @Override
    public List<ClientDto> get() {

        List<Client> clientList = clientRepository.findAll();
        List<ClientDto> dtoList = new ArrayList<>();
        for (Client client : clientList) {
            ClientDto dto = new ClientDto(
                    client.getFullName(),
                    client.getPhoneNumber(),
                    client.isEnabled(),
                    client.getShop().getName()
            );
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public ApiResponse completed(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client", "id", true));
        if (client.isEnabled()) {
            client.setEnabled(false);
        }else {
            client.setEnabled(true);
        }
        clientRepository.save(client);
        return new ApiResponse("Completed ", true);
    }

    @Override
    public ClientDto getOne(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client", "id", true));
        ClientDto dto = new ClientDto(
                client.getFullName(),
                client.getPhoneNumber(),
                client.isEnabled(),
                client.getShop().getName()
        );
        return dto;
    }
}
