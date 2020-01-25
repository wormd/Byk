package byk.app.repository;

import byk.app.model.CashBookLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashBookLineRepository extends JpaRepository<CashBookLine, Long> { }
