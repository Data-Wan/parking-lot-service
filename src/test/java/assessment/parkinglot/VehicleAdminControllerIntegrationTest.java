package assessment.parkinglot;

import assessment.parkinglot.vehicle.application.dto.VehicleDto;
import assessment.parkinglot.vehicle.presentation.controller.api.VehicleAdminApiUrl;
import assessment.parkinglot.vehicle.presentation.controller.dto.request.IsAvailableForVehicleTypeRequest;
import assessment.parkinglot.vehicle.presentation.controller.dto.request.IsFullForVehicleTypeRequest;
import assessment.parkinglot.vehicle.presentation.controller.dto.request.LeaveParkingVehicleRequest;
import assessment.parkinglot.vehicle.presentation.controller.dto.request.ParkVehicleRequest;
import assessment.parkinglot.vehicle.presentation.controller.dto.response.ParkVehicleResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class VehicleAdminControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAll() throws Exception {
        for (int i = 0; i < 5; i++) {
            ParkVehicleRequest request = new ParkVehicleRequest(VehicleDto.VehicleType.MOTORCYCLE);

            mockMvc.perform(post(VehicleAdminApiUrl.V1.Vehicle.PARK).contentType(MediaType.APPLICATION_JSON)
                                                                    .content(objectMapper.writeValueAsString(request)))
                   .andExpect(status().isOk());
        }

        mockMvc.perform(post(VehicleAdminApiUrl.V1.Vehicle.LIST)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vehicles").isArray())
                .andExpect(jsonPath("$.vehicles[*].id").isNotEmpty())
                .andExpect(jsonPath("$.vehicles[*].type").isNotEmpty())
                .andExpect(jsonPath("$.vehicles[*].parkingSpaceIds").isArray());
    }

    @Test
    void testParkVehicle() throws Exception {
        ParkVehicleRequest request = new ParkVehicleRequest(VehicleDto.VehicleType.CAR);

        mockMvc.perform(post(VehicleAdminApiUrl.V1.Vehicle.PARK)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.type").value(VehicleDto.VehicleType.CAR.name()))
                .andExpect(jsonPath("$.parkingSpaceIds").isArray());
    }

    @Test
    void testParkAndLeaveParking() throws Exception {
        ParkVehicleRequest parkVehicleRequest = new ParkVehicleRequest(VehicleDto.VehicleType.CAR);

        var responseString = mockMvc.perform(
            post(VehicleAdminApiUrl.V1.Vehicle.PARK)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(parkVehicleRequest))
                                    )
                                    .andExpect(status().isOk())
                                    .andExpect(jsonPath("$.id").isNotEmpty())
                                    .andExpect(jsonPath("$.type").value(VehicleDto.VehicleType.CAR.name()))
                                    .andExpect(jsonPath("$.parkingSpaceIds").isArray())
                                     .andReturn()
                                     .getResponse()
                                     .getContentAsString();
        var response = objectMapper.readValue(responseString, ParkVehicleResponse.class);
        UUID vehicleId = response.getId();
        LeaveParkingVehicleRequest leaveParkingVehicleRequest = new LeaveParkingVehicleRequest(vehicleId);

        mockMvc.perform(post(VehicleAdminApiUrl.V1.Vehicle.LEAVE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(leaveParkingVehicleRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void testIsAvailableTrueForVehicleType() throws Exception {
        for (int i = 0; i < 4; i++) {
            ParkVehicleRequest request = new ParkVehicleRequest(VehicleDto.VehicleType.MOTORCYCLE);

            mockMvc.perform(post(VehicleAdminApiUrl.V1.Vehicle.PARK).contentType(MediaType.APPLICATION_JSON)
                                                                    .content(objectMapper.writeValueAsString(request)))
                   .andExpect(status().isOk());
        }


        IsAvailableForVehicleTypeRequest request = new IsAvailableForVehicleTypeRequest(VehicleDto.VehicleType.MOTORCYCLE);

        mockMvc.perform(get(VehicleAdminApiUrl.V1.Vehicle.AVAILABLE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void testIsAvailableFalseForVehicleType() throws Exception {
        for (int i = 0; i < 5; i++) {
            ParkVehicleRequest request = new ParkVehicleRequest(VehicleDto.VehicleType.MOTORCYCLE);

            mockMvc.perform(post(VehicleAdminApiUrl.V1.Vehicle.PARK).contentType(MediaType.APPLICATION_JSON)
                                                                    .content(objectMapper.writeValueAsString(request)))
                   .andExpect(status().isOk());
        }


        IsAvailableForVehicleTypeRequest request = new IsAvailableForVehicleTypeRequest(VehicleDto.VehicleType.MOTORCYCLE);

        mockMvc.perform(get(VehicleAdminApiUrl.V1.Vehicle.AVAILABLE)
                   .contentType(MediaType.APPLICATION_JSON)
                   .content(objectMapper.writeValueAsString(request)))
               .andExpect(status().isOk())
               .andExpect(content().string("false"));
    }

    @Test
    void testIsFullTrueForVehicleType() throws Exception {
        for (int i = 0; i < 5; i++) {
            ParkVehicleRequest request = new ParkVehicleRequest(VehicleDto.VehicleType.MOTORCYCLE);

            mockMvc.perform(post(VehicleAdminApiUrl.V1.Vehicle.PARK).contentType(MediaType.APPLICATION_JSON)
                                                                    .content(objectMapper.writeValueAsString(request)))
                   .andExpect(status().isOk());
        }


        IsFullForVehicleTypeRequest request = new IsFullForVehicleTypeRequest(VehicleDto.VehicleType.MOTORCYCLE);

        mockMvc.perform(get(VehicleAdminApiUrl.V1.Vehicle.FULL)
                   .contentType(MediaType.APPLICATION_JSON)
                   .content(objectMapper.writeValueAsString(request)))
               .andExpect(status().isOk())
               .andExpect(content().string("true"));
    }

    @Test
    void testFailToPark() throws Exception {
        for (int i = 0; i < 5; i++) {
            ParkVehicleRequest request = new ParkVehicleRequest(VehicleDto.VehicleType.MOTORCYCLE);

            mockMvc.perform(post(VehicleAdminApiUrl.V1.Vehicle.PARK).contentType(MediaType.APPLICATION_JSON)
                                                                    .content(objectMapper.writeValueAsString(request)))
                   .andExpect(status().isOk());
        }


        ParkVehicleRequest request = new ParkVehicleRequest(VehicleDto.VehicleType.MOTORCYCLE);

        mockMvc.perform(post(VehicleAdminApiUrl.V1.Vehicle.PARK).contentType(MediaType.APPLICATION_JSON)
                                                                .content(objectMapper.writeValueAsString(request)))
               .andExpect(status().isForbidden());
    }
}
