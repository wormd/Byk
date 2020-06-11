package byk.app.repository;

import byk.app.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("select t from Transaction t where (t.origin.id = ?1 or t.target.id = ?1) and (t.date >= ?2 and t.date <= ?3)")
    Iterable<Transaction> findByAccountAndDates(Long id, LocalDate after, LocalDate before);

    @Query("select t from Transaction t where ((?1 is null or t.created >= ?1) and (?2 is null or t.created <= ?2))")
    Page<Transaction> findByCreatedAndDates(LocalDateTime after, LocalDateTime before, Pageable pageable);

    @Query("select t from Transaction t where ((?1 is null or t.date >= ?1) and (?2 is null or t.date <= ?2))")
    Page<Transaction> findByDateAndDates(LocalDateTime after, LocalDateTime before, Pageable pageable);

    @Query("select t from Transaction t where (t.origin.id in ?1 or t.target.id in ?1) and (t.date >= ?2 and t.date <= ?3)")
    Iterable<Transaction> findByAccountListAndDate(List<Long> ids, LocalDate after, LocalDate before);
}
