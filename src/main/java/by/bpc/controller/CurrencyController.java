package by.bpc.controller;

import by.bpc.dto.RateDTO;
import by.bpc.dto.RateDTOView;
import by.bpc.service.CurrencyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/rate")
@Api(tags = "API functionality of working with the RATE")
public class CurrencyController {
    private final CurrencyService currencyService;

    @GetMapping("/{date}")
    @ApiOperation("The endpoint for getting all exchange rates for the day")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The exchange rates have been found"),
            @ApiResponse(code = 404, message = "The exchange rates not found"),
            @ApiResponse(code = 500, message = "Server error")})
    public List<RateDTO> getRatesByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return currencyService.getRatesByDate(localDate);
    }
    @GetMapping("/{date}/{curId}")
    @ApiOperation("The endpoint for getting exchange rate for the day by currency id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The exchange rates have been found"),
            @ApiResponse(code = 404, message = "The exchange rates not found"),
            @ApiResponse(code = 500, message = "Server error")})
    public RateDTOView getRateByDateAndCurId(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, @PathVariable Long curId) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return currencyService.getRateByDateAndCurId(localDate,curId);
    }
}
