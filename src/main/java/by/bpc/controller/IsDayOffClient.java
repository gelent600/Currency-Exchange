package by.bpc.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "is-day-off", url = "https://isdayoff.ru")
public interface IsDayOffClient {
    @GetMapping("/{date}?cc=by")
    String isDayOff(@PathVariable String date);
}
