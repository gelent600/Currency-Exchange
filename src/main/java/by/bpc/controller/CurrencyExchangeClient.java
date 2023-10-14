package by.bpc.controller;

import by.bpc.dto.RateDTOFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name = "currency-exchange", url = "https://api.nbrb.by")
public interface CurrencyExchangeClient {
    @GetMapping("/exrates/rates?periodicity=0")
    List<RateDTOFeign> getCurrencyRates(@RequestParam("ondate") String date);

    @GetMapping("/exrates/rates/{curId}")
    RateDTOFeign getCurrencyRateByDateAndCurId(@PathVariable String curId, @RequestParam("ondate") String date);
}
