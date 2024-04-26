package assessment.parkinglot.parkingspace.application.service;

import assessment.parkinglot.parkingspace.application.dto.ParkingSpaceDto;
import assessment.parkinglot.parkingspace.infrastructure.entity.ParkingSpaceEntity;
import assessment.parkinglot.parkingspace.infrastructure.repository.ParkingSpaceRepository;
import assessment.parkinglot.parkingspace.application.exception.ReserveParkingSpaceFailedNoEmptySpacesException;
import assessment.parkinglot.vehicle.application.dto.VehicleDto;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParkingSpaceService {

    private final ParkingSpaceRepository parkingSpaceRepository;

    public List<ParkingSpaceDto> getRemainingSpaces() {
        List<ParkingSpaceDto> parkingSpaceDtos = new ArrayList<>();
        for (ParkingSpaceEntity parkingSpaceEntity : parkingSpaceRepository.findAll()) {

            ParkingSpaceDto.ParkingSpaceType type = switch(parkingSpaceEntity.getType()) {
                case COMPACT -> ParkingSpaceDto.ParkingSpaceType.COMPACT;
                case MOTORCYCLE -> ParkingSpaceDto.ParkingSpaceType.MOTORCYCLE;
                case REGULAR -> ParkingSpaceDto.ParkingSpaceType.REGULAR;
            };
            parkingSpaceDtos.add(new ParkingSpaceDto(
                parkingSpaceEntity.getId(),
                type,
                parkingSpaceEntity.getIsAvailable()
            ));
        }
        return parkingSpaceDtos;
    }

    public long getRemainingSpacesCount(VehicleDto.VehicleType type) {
        var list = switch (type) {
            case CAR -> List.of(ParkingSpaceEntity.ParkingSpaceType.COMPACT, ParkingSpaceEntity.ParkingSpaceType.REGULAR);
            case MOTORCYCLE -> List.of(ParkingSpaceEntity.ParkingSpaceType.MOTORCYCLE);
            case VAN -> List.of(ParkingSpaceEntity.ParkingSpaceType.REGULAR);
        };
        return parkingSpaceRepository.countByTypeIn(list);
    }

    public void makeSpaceAvailable(List<UUID> parkingSpaceIds) {
        parkingSpaceRepository.updateIsAvailableByIds(parkingSpaceIds);
    }

    public List<ParkingSpaceEntity> reserveAvailableParkingSpace(VehicleDto.VehicleType type) {
        List<ParkingSpaceEntity.ParkingSpaceType> list;
        int limit;
        switch (type) {
            case CAR -> {
                list = List.of(ParkingSpaceEntity.ParkingSpaceType.COMPACT, ParkingSpaceEntity.ParkingSpaceType.REGULAR);
                limit = 1;
            }
            case MOTORCYCLE -> {
                list = List.of(ParkingSpaceEntity.ParkingSpaceType.MOTORCYCLE);
                limit = 1;
            }
            case VAN -> {
                list = List.of(ParkingSpaceEntity.ParkingSpaceType.REGULAR);
                limit = 3;
            }
            default -> throw new IllegalArgumentException();
        };

        var parkingSpaceEntities = parkingSpaceRepository.findDistinctByTypeIn(list, PageRequest.ofSize(limit));
        if (parkingSpaceEntities.size() < limit) {
           throw new ReserveParkingSpaceFailedNoEmptySpacesException();
        }
        parkingSpaceRepository.reserveIsAvailableByIds(parkingSpaceEntities.stream().map(ParkingSpaceEntity::getId).toList());

        return parkingSpaceEntities;
    }
}
