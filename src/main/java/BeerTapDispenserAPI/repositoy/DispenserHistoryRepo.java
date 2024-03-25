package BeerTapDispenserAPI.repositoy;

import BeerTapDispenserAPI.entity.DispenserHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DispenserHistoryRepo extends JpaRepository<DispenserHistory, UUID> {
    Optional<List<DispenserHistory>> findAllByDispenserId(UUID id);
    Optional<DispenserHistory> findTopByClosedAtIsNullOrderByOpenedAtDesc();
}
