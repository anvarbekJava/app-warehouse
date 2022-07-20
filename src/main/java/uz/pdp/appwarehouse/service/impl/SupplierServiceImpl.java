package uz.pdp.appwarehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.domain.Shop;
import uz.pdp.appwarehouse.domain.Supplier;
import uz.pdp.appwarehouse.exseption.ResourceNotFoundException;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.SupplierDto;
import uz.pdp.appwarehouse.repository.ShopRepository;
import uz.pdp.appwarehouse.repository.SupplierRepository;
import uz.pdp.appwarehouse.service.SupplierService;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    ShopRepository shopRepository;

    @Override
    public ApiResponse add(SupplierDto dto) {
        if (supplierRepository.existsByPhoneNumber(dto.getPhoneNumber()))
            return new ApiResponse("Bunday Supplier bor", false);
        Shop shop = shopRepository.findById(dto.getShopId()).orElseThrow(() -> new ResourceNotFoundException("Supplier", "shop", false));
        Supplier supplier = new Supplier(
                dto.getFullName(),
                dto.getPhoneNumber(),
                shop
        );
        supplierRepository.save(supplier);
        return new ApiResponse("Saqlandi supllier", true);
    }

    @Override
    public List<SupplierDto> get() {
        List<Supplier> supplierList = supplierRepository.findAll();
        List<SupplierDto> dtoList = new ArrayList<>();
        for (Supplier supplier : supplierList) {
            SupplierDto supplierDto = new SupplierDto();
            supplierDto.setFullName(supplier.getFullName());
            supplierDto.setPhoneNumber(supplier.getPhoneNumber());
            supplierDto.setShopName(supplier.getShop().getName());
            supplierDto.setEnabled(supplier.isEnabled());
            dtoList.add(supplierDto);
        }
        return dtoList;
    }

    @Override
    public ApiResponse completed(Long id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Supplier", "enabled", false));
        if (supplier.isEnabled()){
            supplier.setEnabled(false);
        }else {
            supplier.setEnabled(true);
        }
        supplierRepository.save(supplier);
        return new ApiResponse("Ozgartildi", true);
    }

    @Override
    public ApiResponse edet(SupplierDto dto, Long id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Supplier", "enabled", false));
        Shop shop = shopRepository.findById(dto.getShopId()).orElseThrow(() -> new ResourceNotFoundException("Supplier", "shop", false));
        supplier.setFullName(dto.getFullName());
        supplier.setPhoneNumber(dto.getPhoneNumber());
        supplier.setShop(shop);
        supplierRepository.save(supplier);
        return new ApiResponse("Edet supplier", true);
    }

    @Override
    public Supplier getOne(Long id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Supplier", "id", true));
        return supplier;
    }
}
