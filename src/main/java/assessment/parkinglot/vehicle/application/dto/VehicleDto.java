package assessment.parkinglot.vehicle.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public final class VehicleDto {

    private final UUID id;

    private final VehicleType type;

    private final List<UUID> parkingSpacesIds;

    public enum VehicleType {
        CAR,
        MOTORCYCLE,
        VAN;

        @Override
        public String toString() {
            return name();
        }
    }
}
