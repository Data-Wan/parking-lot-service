package assessment.parkinglot;

import assessment.parkinglot.parkingspace.presentation.controller.api.ParkingSpaceAdminApiUrl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class ParkingSpaceAdminControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetRemainingSpaces() throws Exception {
        mockMvc.perform(get(ParkingSpaceAdminApiUrl.V1.ParkingSpace.REMAINING)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.remainingSpaceList").isArray())
                .andExpect(jsonPath("$.remainingSpaceList[*].id").isNotEmpty())
                .andExpect(jsonPath("$.remainingSpaceList[*].type").isNotEmpty())
                .andExpect(jsonPath("$.remainingSpaceList[*].isAvailable").isNotEmpty());
    }
}
