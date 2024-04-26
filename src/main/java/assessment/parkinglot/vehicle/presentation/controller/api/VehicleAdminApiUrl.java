package assessment.parkinglot.vehicle.presentation.controller.api;

public interface VehicleAdminApiUrl {

    interface V1 {
        String VERSION = "v1";
        String API_ROOT_PATH = "/" + VERSION + "/api";

        interface Vehicle {
            String BASE = API_ROOT_PATH + "/vehicles";
            String LIST = BASE;
            String PARK = BASE + "/park";
            String LEAVE = BASE + "/leave";
            String AVAILABLE = BASE + "/available";
            String FULL = BASE + "/full";
        }
    }
}
