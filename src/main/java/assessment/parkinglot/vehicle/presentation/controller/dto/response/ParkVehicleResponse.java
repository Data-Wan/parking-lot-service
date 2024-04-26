package assessment.parkinglot.vehicle.presentation.controller.dto.response;

import assessment.parkinglot.vehicle.application.dto.VehicleDto;
import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public final class ParkVehicleResponse {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("type")
    @Schema(
        description = "Type of parking Vehicle",
        example = "CAR",
        implementation = VehicleDto.VehicleType.class
    )
    private VehicleDto.VehicleType type;

    @JsonProperty("parkingSpaceIds")
    private List<UUID> parkingSpaceIds;
}
