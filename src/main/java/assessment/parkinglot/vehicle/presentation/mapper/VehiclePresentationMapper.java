package assessment.parkinglot.vehicle.presentation.mapper;

import assessment.parkinglot.vehicle.application.dto.VehicleDto;
import assessment.parkinglot.vehicle.presentation.controller.dto.response.GetVehicleListResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class VehiclePresentationMapper {

    public GetVehicleListResponse toGetVehicleListResponse(List<VehicleDto> vehicleDtos) {
        var vehicleResponses = vehicleDtos.stream()
                              .map(vehicleDto -> new GetVehicleListResponse.VehicleResponse(
                                  vehicleDto.getId(),
                                  vehicleDto.getType(),
                                  vehicleDto.getParkingSpacesIds())
                              )
                              .toList();

        return new GetVehicleListResponse(vehicleResponses);
    }
}
