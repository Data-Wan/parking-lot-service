package assessment.parkinglot.vehicle.infrastructure.repository;

import assessment.parkinglot.parkingspace.infrastructure.entity.ParkingSpaceEntity;
import assessment.parkinglot.vehicle.infrastructure.entity.VehicleEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface VehicleRepository extends CrudRepository<VehicleEntity, UUID> {

    @Transactional
    @Modifying
    @Query("delete from VehicleEntity v where v.id = :id")
    int deleteByType(UUID id);

    @Query("select v.parkingSpaces from VehicleEntity v where v.id = :id")
    List<ParkingSpaceEntity> findParkingSpacesById(UUID id);
}
