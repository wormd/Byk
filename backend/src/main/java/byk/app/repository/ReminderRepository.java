package byk.app.repository;

import byk.app.model.Reminder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    @Query("select r from Reminder r where "+
                                        "(:after is null or :after >= r.dueBy) and "+
                                        "(:before is null or :before <= r.dueBy) and "+
                                        "r.done = :done")
    Page<Reminder> findAllByParams(LocalDateTime after, LocalDateTime before, Boolean done, Pageable pageable);
}
