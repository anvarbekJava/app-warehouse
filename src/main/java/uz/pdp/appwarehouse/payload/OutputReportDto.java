package uz.pdp.appwarehouse.payload;

import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class OutputReportDto {
    private Integer code;

    private String warehouseName;

    private String worker;

    private String workerUrl;

    private String clientName;

    private String clientUrl;

    private LocalTime date;

    private String facturaNumber;

    private Double summa;

    private String valyuta;

    private List<OutputProductDto> productDtoList;

    public OutputReportDto(Integer code, String warehouseName, String worker, String workerUrl, String clientName, String clientUrl, LocalTime date, String facturaNumber, Double summa, String valyuta) {
        this.code = code;
        this.warehouseName = warehouseName;
        this.worker = worker;
        this.workerUrl = workerUrl;
        this.clientName = clientName;
        this.clientUrl = clientUrl;
        this.date = date;
        this.facturaNumber = facturaNumber;
        this.summa = summa;
        this.valyuta = valyuta;
    }
}
