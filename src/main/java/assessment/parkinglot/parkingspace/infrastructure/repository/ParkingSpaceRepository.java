package assessment.parkinglot.parkingspace.infrastructure.repository;

import assessment.parkinglot.parkingspace.infrastructure.entity.ParkingSpaceEntity;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ParkingSpaceRepository extends CrudRepository<ParkingSpaceEntity, UUID> {

    @Query("select count(p) from ParkingSpaceEntity p where p.type in :types and p.isAvailable = true")
    long countByTypeIn(Collection<ParkingSpaceEntity.ParkingSpaceType> types);

    @Query(value = "select p from ParkingSpaceEntity p where p.type in :types and p.isAvailable = true")
    List<ParkingSpaceEntity> findDistinctByTypeIn(Collection<ParkingSpaceEntity.ParkingSpaceType> types, Pageable pageable);

    @Transactional
    @Modifying
    @Query("update ParkingSpaceEntity p set p.isAvailable = true where p.id in :parkingSpaceIds")
    void updateIsAvailableByIds(List<UUID> parkingSpaceIds);

    @Transactional
    @Modifying
    @Query("update ParkingSpaceEntity p set p.isAvailable = false where p.id in :parkingSpaceIds")
    void reserveIsAvailableByIds(List<UUID> parkingSpaceIds);
}
