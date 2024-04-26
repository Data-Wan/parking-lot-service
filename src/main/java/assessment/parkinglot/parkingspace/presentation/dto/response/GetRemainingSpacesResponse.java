package assessment.parkinglot.parkingspace.presentation.dto.response;

import assessment.parkinglot.parkingspace.application.dto.ParkingSpaceDto;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.*;
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
public class GetRemainingSpacesResponse {

    @JsonProperty("remainingSpaceList")
    private List<RemainingSpaceResponse> remainingSpaceList;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class RemainingSpaceResponse {
        @JsonProperty("id")
        private UUID id;

        @JsonProperty("type")
        private ParkingSpaceDto.ParkingSpaceType type;

        @JsonProperty("isAvailable")
        private Boolean isAvailable;
    }
}
