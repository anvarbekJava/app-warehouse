package uz.pdp.appwarehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.domain.Currency;
import uz.pdp.appwarehouse.exseption.ResourceNotFoundException;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.CurrencyDto;
import uz.pdp.appwarehouse.repository.CurrencyRepository;
import uz.pdp.appwarehouse.service.CurrencyService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;

    @Override
    public ApiResponse add(CurrencyDto dto) {
        if (currencyRepository.existsByName(dto.getName()))
            return new ApiResponse("Bunday valyuta mavjud!", false);
        Currency currency = new Currency(
                dto.getName()
        );
        currencyRepository.save(currency);
        return new ApiResponse("Saqlandi", true);
    }

    @Override
    public ApiResponse completed(Long id) {
        Currency currency = currencyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Currency", "id", true));
        if (currency.isEnabled()){
            currency.setEnabled(false);
        }else {
            currency.setEnabled(true);
        }
        currencyRepository.save(currency);
        return new ApiResponse("Completed", true);
    }


    @Override
    public List<CurrencyDto> get() {
        List<Currency> currencyList = currencyRepository.findAll();
        List<CurrencyDto> dtoList = new ArrayList<>();
        for (Currency currency : currencyList) {
            CurrencyDto currencyDto = new CurrencyDto();
            currencyDto.setName(currency.getName());
            currencyDto.setEnabled(currency.isEnabled());
            dtoList.add(currencyDto);
        }
        return dtoList;
    }
}
