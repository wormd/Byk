package byk.app.repository;

import byk.app.model.Cashbookline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CashbooklineRepository extends JpaRepository<Cashbookline, Long> {

    Iterable<Cashbookline> findByDateAfterAndDateBefore(LocalDate after, LocalDate before);

}
