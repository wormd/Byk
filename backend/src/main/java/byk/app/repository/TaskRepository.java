package byk.app.repository;

import byk.app.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("select r from Task r where "+
                                        "(cast(:after as date) is null or :after >= r.dueBy) and "+
                                        "(cast(:before as date) is null or :before <= r.dueBy) and "+
                                        "r.done = :done")
    Page<Task> findAllByParams(LocalDateTime after, LocalDateTime before, Boolean done, Pageable pageable);
}
