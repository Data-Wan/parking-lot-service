package assessment.parkinglot.vehicle.application.service;

import assessment.parkinglot.parkingspace.application.service.ParkingSpaceService;
import assessment.parkinglot.parkingspace.infrastructure.entity.ParkingSpaceEntity;
import assessment.parkinglot.vehicle.application.dto.VehicleDto;
import assessment.parkinglot.vehicle.infrastructure.entity.VehicleEntity;
import assessment.parkinglot.vehicle.infrastructure.repository.VehicleRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleAdminService {
    private final VehicleRepository vehicleRepository;
    private final ParkingSpaceService parkingSpaceService;

    public List<VehicleDto> getAll() {
        // possible to extract to mapstruct in application module
        List<VehicleDto> vehicleDtos = new ArrayList<>();
        for (VehicleEntity vehicleEntity : vehicleRepository.findAll()) {
            VehicleDto.VehicleType type =
                switch(vehicleEntity.getType()) {
                    case CAR -> VehicleDto.VehicleType.CAR;
                    case MOTORCYCLE -> VehicleDto.VehicleType.MOTORCYCLE;
                    case VAN -> VehicleDto.VehicleType.VAN;
                };
            var parkingSpacesIds = vehicleEntity.getParkingSpaces()
                                                .stream()
                                                .map(ParkingSpaceEntity::getId)
                                                .toList();
            vehicleDtos.add(new VehicleDto(
                vehicleEntity.getId(),
                type, parkingSpacesIds
            ));
        }

        return vehicleDtos;
    }

    @Transactional
    public VehicleDto parkVehicle(VehicleDto.VehicleType type) {
        // possible to extract to mapstruct in infrastructure module
        var vehicleEntity = new VehicleEntity();
        VehicleEntity.VehicleType entityType =
            switch(type) {
                case CAR -> VehicleEntity.VehicleType.CAR;
                case MOTORCYCLE -> VehicleEntity.VehicleType.MOTORCYCLE;
                case VAN -> VehicleEntity.VehicleType.VAN;
            };

        vehicleEntity.setType(entityType);

        var availableParkingSpace = parkingSpaceService.reserveAvailableParkingSpace(type);
        vehicleEntity.setParkingSpaces(availableParkingSpace);
        var saved = vehicleRepository.save(vehicleEntity);

        var parkingSpacesIds = saved.getParkingSpaces()
                        .stream()
                        .map(ParkingSpaceEntity::getId)
                        .toList();
        return new VehicleDto(
            saved.getId(),
            type,
            parkingSpacesIds);
    }

    @Transactional
    public void leaveParking(UUID id) {
        var parkingSpacesIds = vehicleRepository.findParkingSpacesById(id)
            .stream().map(ParkingSpaceEntity::getId).toList();
        parkingSpaceService.makeSpaceAvailable(parkingSpacesIds);
        vehicleRepository.deleteById(id);
    }

    public Boolean isAvailableForVehicleType(VehicleDto.VehicleType type) {
        var remainingSpacesCount = parkingSpaceService.getRemainingSpacesCount(type);
        return switch (type) {
            case CAR -> remainingSpacesCount >= 1L;
            case MOTORCYCLE -> remainingSpacesCount >= 1L;
            case VAN -> (remainingSpacesCount / 3) >= 1L;
        };
    }
}
