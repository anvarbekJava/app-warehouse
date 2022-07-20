package uz.pdp.appwarehouse.controller;

import lombok.Lombok;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.component.CurrentUser;
import uz.pdp.appwarehouse.domain.Users;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.ClientDto;
import uz.pdp.appwarehouse.service.ClientService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    @Autowired
    ClientService clientService;

    @PostMapping("/add")
    public HttpEntity<?> add(@Valid @RequestBody ClientDto dto){
        ApiResponse apiResponse = clientService.add(dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
    @PutMapping("/edet/{id}")
    public HttpEntity<?> edet(@Valid @RequestBody ClientDto dto, @PathVariable Long id){
        ApiResponse apiResponse = clientService.edet(dto, id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable Long id){
        ApiResponse apiResponse = clientService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
    @GetMapping("/get")
    public HttpEntity<?> get(){
        List<ClientDto> clientList = clientService.get();
        return ResponseEntity.ok(clientList);
    }
    @PutMapping("/completed/{id}")
    public HttpEntity<?> completed(@PathVariable Long id){
        ApiResponse apiResponse = clientService.completed(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
    @GetMapping("/get/{id}")
    public HttpEntity<?> getOne(@PathVariable Long id){
        ClientDto clientDto = clientService.getOne(id);
        return ResponseEntity.ok(clientDto);
    }
}
