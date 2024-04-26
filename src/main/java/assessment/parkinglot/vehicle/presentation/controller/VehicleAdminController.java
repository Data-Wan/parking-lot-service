package assessment.parkinglot.vehicle.presentation.controller;

import assessment.parkinglot.vehicle.application.dto.VehicleDto;
import assessment.parkinglot.vehicle.application.service.VehicleAdminService;
import assessment.parkinglot.vehicle.presentation.controller.api.VehicleAdminApiUrl;
import assessment.parkinglot.vehicle.presentation.controller.dto.request.IsAvailableForVehicleTypeRequest;
import assessment.parkinglot.vehicle.presentation.controller.dto.request.IsFullForVehicleTypeRequest;
import assessment.parkinglot.vehicle.presentation.controller.dto.request.LeaveParkingVehicleRequest;
import assessment.parkinglot.vehicle.presentation.controller.dto.request.ParkVehicleRequest;
import assessment.parkinglot.vehicle.presentation.controller.dto.response.GetVehicleListResponse;
import assessment.parkinglot.vehicle.presentation.controller.dto.response.ParkVehicleResponse;
import assessment.parkinglot.vehicle.presentation.mapper.VehiclePresentationMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping
public class VehicleAdminController {

    private final VehicleAdminService vehicleService;
    private final VehiclePresentationMapper mapper;

    @PostMapping(VehicleAdminApiUrl.V1.Vehicle.LIST)
    public ResponseEntity<GetVehicleListResponse> getAll() {
        List<VehicleDto> vehicleDtos = vehicleService.getAll();

        return ResponseEntity.ok(mapper.toGetVehicleListResponse(vehicleDtos));
    }

    @PostMapping(VehicleAdminApiUrl.V1.Vehicle.PARK)
    public ResponseEntity<ParkVehicleResponse> parkVehicle(@RequestBody ParkVehicleRequest parkVehicleRequest) {
        VehicleDto vehicleDto = vehicleService.parkVehicle(parkVehicleRequest.getType());
        var response = new ParkVehicleResponse(vehicleDto.getId(), vehicleDto.getType(), vehicleDto.getParkingSpacesIds());
        return ResponseEntity.ok(response);
    }

    @PostMapping(VehicleAdminApiUrl.V1.Vehicle.LEAVE)
    public void leaveParking(@RequestBody LeaveParkingVehicleRequest leaveParkingVehicleRequest) {
        vehicleService.leaveParking(leaveParkingVehicleRequest.getId());
    }

    // this one better
    @GetMapping(VehicleAdminApiUrl.V1.Vehicle.AVAILABLE)
    public ResponseEntity<Boolean> isAvailableForVehicleType(@RequestBody IsAvailableForVehicleTypeRequest request) {
        return ResponseEntity.ok(vehicleService.isAvailableForVehicleType(request.getType()));
    }

    @GetMapping(VehicleAdminApiUrl.V1.Vehicle.FULL)
    public ResponseEntity<Boolean> isFullForVehicleType(@RequestBody IsFullForVehicleTypeRequest request) {
        return ResponseEntity.ok(!vehicleService.isAvailableForVehicleType(request.getType()));
    }
}
