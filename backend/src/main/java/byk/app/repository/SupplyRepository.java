package byk.app.repository;

import byk.app.model.Supply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplyRepository extends JpaRepository<Supply, Long> {
}
