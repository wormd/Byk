package byk.app.repository;

import byk.app.model.Account;
import byk.app.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Iterable<Transaction> findByDateAfterAndDateBefore(LocalDate after, LocalDate before);

    Iterable<Transaction> findByAccountAndDateAfterAndDateBefore(Account account, LocalDate after, LocalDate before);

}
