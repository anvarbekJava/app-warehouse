package uz.pdp.appwarehouse.payload;

import lombok.Data;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
public class InputReportDto {

    private Integer code;

    private String warehouseName;

    private String worker;

    private String workerUrl;

    private String supplierName;

    private String supplierUrl;

    private Date date;

    private String facturaNumber;

    private Double summa;

    private String valyuta;

    private List<InputProductDto> productDtoList;

    public InputReportDto(Integer code, String warehouseName, String worker, String workerUrl, String supplierName, String supplierUrl, Date date, String facturaNumber, Double summa, String valyuta) {
        this.code = code;
        this.warehouseName = warehouseName;
        this.worker = worker;
        this.workerUrl = workerUrl;
        this.supplierName = supplierName;
        this.supplierUrl = supplierUrl;
        this.date = date;
        this.facturaNumber = facturaNumber;
        this.summa = summa;
        this.valyuta = valyuta;
    }
}
