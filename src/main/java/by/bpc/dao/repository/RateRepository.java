package by.bpc.dao.repository;

import by.bpc.dao.beans.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
    List<Rate> getAllByDate(LocalDate date);

    Optional<Rate> getRateByDateAndCurId(LocalDate date, Long curId);
}
