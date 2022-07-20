package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uz.pdp.appwarehouse.domain.Input;
import uz.pdp.appwarehouse.domain.InputProduct;
import uz.pdp.appwarehouse.domain.Output;
import uz.pdp.appwarehouse.domain.OutputProduct;
import uz.pdp.appwarehouse.payload.InputProductDto;
import uz.pdp.appwarehouse.payload.InputReportDto;
import uz.pdp.appwarehouse.payload.OutputProductDto;
import uz.pdp.appwarehouse.payload.OutputReportDto;
import uz.pdp.appwarehouse.service.InputService;
import uz.pdp.appwarehouse.service.OutputService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javafx.scene.input.KeyCode.L;

@RestController
@RequestMapping("/api/report")
public class DashboardController {
    @Autowired
    InputService inputService;
    @Autowired
    OutputService outputService;

    @GetMapping("/input")
    public HttpEntity<?> getInput(){
        List<Input> reportList = inputService.OneDayPeriod();
        List<InputReportDto> reportDtoList = new ArrayList<>();
        for (Input input : reportList) {
            Double summa = 0.;

            List<InputProductDto> dtoList = new ArrayList<>();
            for (InputProduct inputProduct : input.getInputProduct()) {
                InputProductDto inputProductDto = new InputProductDto(
                        inputProduct.getAmount(),
                        inputProduct.getPrice(),
                        inputProduct.getExpireDate().getTime(),
                        inputProduct.getProduct().getName(),
                        ServletUriComponentsBuilder.fromCurrentContextPath()
                                .path("/api/product/get/")
                                .path(inputProduct.getProduct().getId().toString()).toUriString()
                );
                summa = summa+inputProduct.getSumma();

                dtoList.add(inputProductDto);
            }
            InputReportDto inputReportDto = new InputReportDto(
                    input.getCode(),
                    input.getWarehouse().getName(),
                    input.getUsers().getFirstName()+"  "+input.getUsers().getLastName(),
                    ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/api/member/get/")
                            .path(input.getUsers().getId().toString()).toUriString(),
                    input.getSupplier().getFullName(),
                    ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/api/supplier/get/")
                            .path(input.getSupplier().getId().toString()).toUriString(),
                    input.getDate(),
                    input.getFacturaNumber(),
                    summa,
                    input.getCurrency().getName()
            );
            inputReportDto.setProductDtoList(dtoList);
            reportDtoList.add(inputReportDto);
        }
        return ResponseEntity.ok(reportDtoList);
    }
    @GetMapping("/output")
    public HttpEntity<?> getOutput(){
        List<Output> outputList = outputService.getOneDaysPeriod();
        List<OutputReportDto> reportDtoList = new ArrayList<>();
        for (Output output : outputList) {
            Double summa = 0.;
            List<OutputProductDto> productDtoList = new ArrayList<>();
            for (OutputProduct product : output.getOutputProduct()) {
                OutputProductDto outputProductDto = new OutputProductDto(
                    product.getAmount(),
                    product.getPrice(),
                    product.getProduct().getName(),
                        ServletUriComponentsBuilder.fromCurrentContextPath()
                                .path("/api/product/get/")
                                .path(product.getProduct().getId().toString()).toUriString()
                );
                summa = summa + product.getSumma();
                productDtoList.add(outputProductDto);
            }

            OutputReportDto outputReportDto = new OutputReportDto(
                    output.getCode(),
                    output.getWarehouse().getName(),
                    output.getUsers().getFirstName()+"  "+output.getUsers().getLastName(),
                    ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/api/member/get/")
                            .path(output.getUsers().getId().toString()).toUriString(),
                    output.getClient().getFullName(),
                    ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/api/client/get/")
                            .path(output.getClient().getId().toString()).toUriString(),
                    output.getDate().toLocalDateTime().toLocalTime(),
                    output.getFacturaNumber(),
                    summa,
                    output.getCurrency().getName()
            );
            outputReportDto.setProductDtoList(productDtoList);
            reportDtoList.add(outputReportDto);
        }
        return ResponseEntity.ok(reportDtoList);
    }

    @GetMapping("/getExpresionDate")
    public HttpEntity<?> getExpresionDateProduct(){
        List<InputProductDto> productDtoList = inputService.getExpresionDateProduct();
        return ResponseEntity.ok(productDtoList);
    }


}
