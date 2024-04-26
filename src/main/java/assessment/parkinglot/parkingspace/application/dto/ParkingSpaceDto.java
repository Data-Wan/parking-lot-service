package assessment.parkinglot.parkingspace.application.dto;

import java.util.UUID;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ParkingSpaceDto {
    private final UUID id;

    private final ParkingSpaceType type;

    private final Boolean isAvailable;

    public enum ParkingSpaceType {
        COMPACT,
        MOTORCYCLE,
        REGULAR
    }
}
