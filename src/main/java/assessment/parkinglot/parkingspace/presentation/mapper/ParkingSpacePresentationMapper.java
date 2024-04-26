package assessment.parkinglot.parkingspace.presentation.mapper;

import assessment.parkinglot.parkingspace.application.dto.ParkingSpaceDto;
import assessment.parkinglot.parkingspace.presentation.dto.response.GetRemainingSpacesResponse;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ParkingSpacePresentationMapper {

    public GetRemainingSpacesResponse toGetRemainingSpacesResponse(List<ParkingSpaceDto> remainingSpaces) {
        var remainingSpaceResponses = remainingSpaces.stream()
                                  .map(parkingSpaceDto -> new GetRemainingSpacesResponse.RemainingSpaceResponse(
                                      parkingSpaceDto.getId(),
                                      parkingSpaceDto.getType(),
                                      parkingSpaceDto.getIsAvailable()
                                  ))
                                  .toList();
        return new GetRemainingSpacesResponse(remainingSpaceResponses);
    }
}
