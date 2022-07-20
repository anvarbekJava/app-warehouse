package uz.pdp.appwarehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.domain.Address;
import uz.pdp.appwarehouse.domain.Shop;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.ShopDto;
import uz.pdp.appwarehouse.repository.ShopRepository;
import uz.pdp.appwarehouse.service.ShopService;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    ShopRepository shopRepository;
    @Override
    public ApiResponse add(ShopDto dto) {
        if (shopRepository.existsByName(dto.getName()))
            return new ApiResponse("Bunday shop bor", false);
        Shop shop = new Shop();
        shop.setName(dto.getName());
        Address address = new Address(
                dto.getAddressDto().getCity(),
                dto.getAddressDto().getDistrict(),
                dto.getAddressDto().getStreet(),
                dto.getAddressDto().getHomeNumber(),
                shop,
                null
        );
        shop.setAddress(address);
        shopRepository.save(shop);
        return new ApiResponse("Saqlandi shop", true);
    }
}
