package assessment.parkinglot.parkingspace.presentation.controller;

import assessment.parkinglot.parkingspace.application.dto.ParkingSpaceDto;
import assessment.parkinglot.parkingspace.application.service.ParkingSpaceService;
import assessment.parkinglot.parkingspace.presentation.controller.api.ParkingSpaceAdminApiUrl;
import assessment.parkinglot.parkingspace.presentation.dto.response.GetRemainingSpacesResponse;
import assessment.parkinglot.parkingspace.presentation.mapper.ParkingSpacePresentationMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ParkingSpaceAdminController {
    private final ParkingSpaceService parkingSpaceService;
    private final ParkingSpacePresentationMapper mapper;

    @GetMapping(ParkingSpaceAdminApiUrl.V1.ParkingSpace.REMAINING)
    public GetRemainingSpacesResponse getRemainingSpaces() {
        List<ParkingSpaceDto> remainingSpaces = parkingSpaceService.getRemainingSpaces();
        return mapper.toGetRemainingSpacesResponse(remainingSpaces);
    }
}
