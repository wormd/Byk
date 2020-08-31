package byk.app.repository;

import byk.app.model.Guest;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
}
