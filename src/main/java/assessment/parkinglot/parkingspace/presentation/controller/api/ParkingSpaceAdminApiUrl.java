package assessment.parkinglot.parkingspace.presentation.controller.api;

// TODO move to sdk layer for creating clients or use API first approach
public interface ParkingSpaceAdminApiUrl {
    interface V1 {
        String VERSION = "v1";
        String API_ROOT_PATH = "/" + VERSION + "/api";

        interface ParkingSpace {
            String BASE = API_ROOT_PATH + "/parking-spaces";
            String REMAINING = BASE + "/remaining";
        }
    }
}
