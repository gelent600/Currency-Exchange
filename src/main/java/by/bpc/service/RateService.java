package by.bpc.service;

import by.bpc.dto.RateDTO;
import by.bpc.dto.RateDTOView;

import java.time.LocalDate;
import java.util.List;

public interface RateService {
    List<RateDTO> getRatesByDate(LocalDate date);

    List<RateDTO> saveRates(List<RateDTO> listRates, LocalDate date);

    RateDTOView getRateByDateAndCurId(LocalDate localDate, Long curId);
}
