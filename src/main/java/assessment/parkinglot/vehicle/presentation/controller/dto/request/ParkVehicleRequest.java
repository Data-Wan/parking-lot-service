package assessment.parkinglot.vehicle.presentation.controller.dto.request;

import assessment.parkinglot.vehicle.application.dto.VehicleDto;
import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public final class ParkVehicleRequest {

    @JsonProperty("type")
    @Schema(
        description = "Type of parking Vehicle",
        example = "CAR",
        implementation = VehicleDto.VehicleType.class
    )
    private VehicleDto.VehicleType type;
}
