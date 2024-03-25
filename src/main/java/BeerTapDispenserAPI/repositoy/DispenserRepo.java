package BeerTapDispenserAPI.repositoy;

import BeerTapDispenserAPI.entity.Dispenser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface DispenserRepo extends JpaRepository<Dispenser, UUID> {
}
