package uz.pdp.appwarehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.domain.Address;
import uz.pdp.appwarehouse.domain.Shop;
import uz.pdp.appwarehouse.domain.Warehouse;
import uz.pdp.appwarehouse.exseption.ResourceNotFoundException;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.WarehouseDto;
import uz.pdp.appwarehouse.repository.AddressRepository;
import uz.pdp.appwarehouse.repository.ShopRepository;
import uz.pdp.appwarehouse.repository.WarehouseRepository;
import uz.pdp.appwarehouse.service.WarehouseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    ShopRepository shopRepository;
    @Autowired
    AddressRepository addressRepository;

    @Override
    public ApiResponse add(WarehouseDto dto) {
        if (warehouseRepository.existsByName(dto.getName()))
            return new ApiResponse("Bunday warehouse mavjud", false);
        Optional<Shop> optionalShop = shopRepository.findById(dto.getShopId());
        if (!optionalShop.isPresent())
            return new ApiResponse("Shop topilmadi", false);
        Shop shop = optionalShop.get();
        Warehouse warehouse = new Warehouse();
        warehouse.setName(dto.getName());
        warehouse.setShop(shop);
        Address address = new Address(
                dto.getAddressDto().getCity(),
                dto.getAddressDto().getDistrict(),
                dto.getAddressDto().getStreet(),
                dto.getAddressDto().getHomeNumber(),
                null,
                warehouse
        );
        warehouse.setAddress(address);
        warehouseRepository.save(warehouse);
        return new ApiResponse("Saqlandi", true);
    }

    @Override
    public ApiResponse edet(WarehouseDto dto, Long id) {
        Warehouse warehouse = warehouseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Warehouse", "id", false));
        Shop shop = shopRepository.findById(dto.getShopId()).orElseThrow(() -> new ResourceNotFoundException("Shop", "name", false));
        Address address = addressRepository.findById(warehouse.getAddress().getId()).orElseThrow(() -> new ResourceNotFoundException("Address", "address", false));
        address.setCity(dto.getAddressDto().getCity());
        address.setDistrict(dto.getAddressDto().getDistrict());
        address.setStreet(dto.getAddressDto().getStreet());
        address.setHomeNumber(dto.getAddressDto().getHomeNumber());

        warehouse.setName(dto.getName());
        warehouse.setShop(shop);
        warehouse.setAddress(address);

        warehouseRepository.save(warehouse);
        return new ApiResponse("Edet", true);
    }

    @Override
    public List<WarehouseDto> getAll() {
        List<Warehouse> warehouseList = warehouseRepository.findAll();
        List<WarehouseDto> dtoList = new ArrayList<>();
        for (Warehouse warehouse : warehouseList) {
            WarehouseDto dto = new WarehouseDto();
            dto.setName(warehouse.getName());
            dto.setEnabled(warehouse.isEnabled());
            dto.setShopName(warehouse.getShop().getName());
            dto.setCity(warehouse.getAddress().getCity());
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public ApiResponse completed(Long id) {
        Warehouse warehouse = warehouseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Warehouse", "completed", false));
        if (warehouse.isEnabled())
            warehouse.setEnabled(false);
        warehouse.setEnabled(true);
        warehouseRepository.save(warehouse);
        return new ApiResponse("Completed", true);

    }
}
