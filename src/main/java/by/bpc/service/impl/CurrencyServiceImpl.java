package by.bpc.service.impl;

import by.bpc.controller.CurrencyExchangeClient;
import by.bpc.controller.IsDayOffClient;
import by.bpc.dao.beans.Rate;
import by.bpc.dao.repository.RateRepository;
import by.bpc.dto.RateDTO;
import by.bpc.dto.RateDTOFeign;
import by.bpc.dto.RateDTOView;
import by.bpc.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {
    private final ModelMapper modelMapper;
    private final CurrencyExchangeClient currencyExchangeClient;
    private final RateRepository rateRepository;
    private final IsDayOffClient workDayClient;

    @Override
    public List<RateDTO> getRatesByDate(LocalDate date) {
        List<RateDTOFeign> listRatesDTOFeign = currencyExchangeClient.getCurrencyRates(date.toString());
        List<RateDTO> listRatesDTO = listRatesDTOFeign
                .stream()
                .map(rateDTOFeign -> modelMapper.map(rateDTOFeign, RateDTO.class))
                .collect(Collectors.toList());
        saveRates(listRatesDTO, date);
        return listRatesDTO;
    }

    @Override
    @Transactional
    public List<RateDTO> saveRates(List<RateDTO> listRatesDTO, LocalDate date) {
        List<Rate> rateList = rateRepository.getAllByDate(date);
        listRatesDTO
                .stream()
                .map(rateDTO -> modelMapper.map(rateDTO, Rate.class))
                .map(rate -> {
                    if (!rateList.isEmpty()) {
                        for (Rate item : rateList) {
                            if (!item.getCurId().equals(rate.getCurId()) & !item.getDate().equals(rate.getDate())) {
                                rateRepository.save(rate);
                            }
                        }
                    } else {
                        rateRepository.save(rate);
                    }
                    return rate;
                })
                .collect(Collectors.toList());
        return listRatesDTO;
    }

    @Override
    public RateDTOView getRateByDateAndCurId(LocalDate localDate, Long curId) {
        RateDTOFeign rateDTOFeign = currencyExchangeClient.getCurrencyRateByDateAndCurId(curId.toString(), localDate.toString());
        Rate rate = modelMapper.map(rateDTOFeign, Rate.class);
        Optional<Rate> optionalRate = rateRepository.getRateByDateAndCurId(localDate, curId);
        if (!optionalRate.isPresent()) {
            rateRepository.save(rate);
        }

        LocalDate previousWorkingDay = findPreviousWorkday(localDate.minusDays(1));
        Rate ratePrevious = modelMapper.map(currencyExchangeClient.getCurrencyRateByDateAndCurId(curId.toString(), previousWorkingDay.toString()), Rate.class);

        Double different = rate.getCurOfficialRate() - ratePrevious.getCurOfficialRate();
        RateDTOView rateDTOView = modelMapper.map(rate, RateDTOView.class);
        rateDTOView.setDifferentWithPrevious(different);
        return rateDTOView;
    }

    private LocalDate findPreviousWorkday(LocalDate currentDate) {
        String result = workDayClient.isDayOff(currentDate.toString());
        while (result.equals("1")) {
            currentDate = currentDate.minusDays(1);
            result = workDayClient.isDayOff(currentDate.toString());
        }
        return currentDate;
    }
}
