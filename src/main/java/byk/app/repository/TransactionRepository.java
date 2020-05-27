package byk.app.repository;

import byk.app.model.Account;
import byk.app.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findById(long id);

    Iterable<Transaction> findByDateAfterAndDateBefore(LocalDate after, LocalDate before);

    Iterable<Transaction> findByOriginAndDateAfterAndDateBefore(Account account, LocalDate after, LocalDate before);

    @Query("select t from Transaction t where (t.origin.id = ?1 or t.target.id = ?1) and (t.date >= ?2 and t.date <= ?3)")
    Iterable<Transaction> findByAccountAndDates(Long id, LocalDate after, LocalDate before);

}
