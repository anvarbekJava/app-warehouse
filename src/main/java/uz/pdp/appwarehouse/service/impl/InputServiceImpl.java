package uz.pdp.appwarehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.domain.*;
import uz.pdp.appwarehouse.exseption.ResourceNotFoundException;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.InputDto;
import uz.pdp.appwarehouse.payload.InputProductDto;
import uz.pdp.appwarehouse.payload.InputReportDto;
import uz.pdp.appwarehouse.repository.*;
import uz.pdp.appwarehouse.service.InputService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InputServiceImpl implements InputService {
    @Autowired
    InputRepository inputRepository;
    @Autowired
    InputProductRepository inputProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    CurrencyRepository currencyRepository;



    @Override
    public ApiResponse add(InputDto dto, Users users) {
        Warehouse warehouse = warehouseRepository.findById(dto.getWarehouseId()).orElseThrow(() -> new ResourceNotFoundException("Input", "Warehouse", true));
        Supplier supplier = supplierRepository.findById(dto.getSupplierId()).orElseThrow(() -> new ResourceNotFoundException("Input", "Supplier", true));
        Currency currency = currencyRepository.findById(dto.getCurrencyId()).orElseThrow(() -> new ResourceNotFoundException("Input", "Currency", true));

        Input input = new Input(
                new Timestamp(dto.getDate()),
                warehouse,
                supplier,
                currency,
                dto.getFacturaNumber(),
                users
        );

        List<InputProduct> productList = new ArrayList<>();

        for (InputProductDto inputProductDto : dto.getProductDtoList()) {
            Optional<Products> optionalProducts = productRepository.findById(inputProductDto.getProductId());
            if(!optionalProducts.isPresent())
                return new ApiResponse("Product Topilmadi", false);
            Products products = optionalProducts.get();
            InputProduct inputProduct = new InputProduct(
                    products,
                    inputProductDto.getAmount(),
                    inputProductDto.getPrice(),
                    new Timestamp(inputProductDto.getExpireDate()),
                    input

            );
            productList.add(inputProduct);
        }

        input.setInputProduct(productList);
        inputRepository.save(input);
        return new ApiResponse("Saqlandi product", true);
    }

    @Override
    public List<Input> OneDayPeriod() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Timestamp start = new Timestamp(timestamp.getYear(),timestamp.getMonth(), timestamp.getDate(),0,0,0,0);
        Timestamp end = new Timestamp(timestamp.getYear(),timestamp.getMonth(), timestamp.getDate(),23,59,59,1000);
        List<Input> inputList = inputRepository.getAllProductPeriod(new Timestamp(start.getTime()), new Timestamp(end.getTime()));
        return inputList;
    }

    @Override
    public List<InputProductDto> getExpresionDateProduct() {
            return null;
    }
}
