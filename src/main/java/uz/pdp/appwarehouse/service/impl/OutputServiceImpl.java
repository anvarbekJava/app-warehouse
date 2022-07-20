package uz.pdp.appwarehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.domain.*;
import uz.pdp.appwarehouse.exseption.ResourceNotFoundException;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.OutputDto;
import uz.pdp.appwarehouse.payload.OutputProductDto;
import uz.pdp.appwarehouse.repository.*;
import uz.pdp.appwarehouse.service.OutputService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class OutputServiceImpl implements OutputService {
    @Autowired
    OutputRepository outputRepository;
    @Autowired
    OutputProductRepository outputProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Override
    public ApiResponse add(OutputDto dto, Users users) {
        Warehouse warehouse = warehouseRepository.findById(dto.getWarehouseId()).orElseThrow(() -> new ResourceNotFoundException("Input", "Warehouse", true));
        Currency currency = currencyRepository.findById(dto.getCurrencyId()).orElseThrow(() -> new ResourceNotFoundException("Input", "Currency", true));
        Client client = clientRepository.findById(dto.getClientId()).orElseThrow(() -> new ResourceNotFoundException("Output", "client", true));

        Output output = new Output(
          new Timestamp(dto.getDate()),
          warehouse,
          currency,
          dto.getFacturaNumber(),
          client,
                users
        );

        List<OutputProduct> productList = new ArrayList<>();
        for (OutputProductDto productDto : dto.getProductDtoList()) {
            Products product = productRepository.findById(productDto.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Output", "product", true));
            OutputProduct outputProduct = new OutputProduct(
                    product,
                    productDto.getAmount(),
                    productDto.getPrice(),
                    output
            );
            productList.add(outputProduct);
        }
        output.setOutputProduct(productList);
        outputRepository.save(output);
        return new ApiResponse("Saqlandi", true);
    }

    @Override
    public List<Output> getOneDaysPeriod() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Timestamp start = new Timestamp(timestamp.getYear(),timestamp.getMonth(), timestamp.getDate(),0,0,0,0);
        Timestamp end = new Timestamp(timestamp.getYear(),timestamp.getMonth(), timestamp.getDate(),23,59,59,1000);
        List<Output> outputList = outputRepository.findByDateBetween(start, end);
        return outputList;
    }
}
