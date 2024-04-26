package assessment.parkinglot.common.presentation.configuration;

import assessment.parkinglot.parkingspace.presentation.controller.api.ParkingSpaceAdminApiUrl;
import assessment.parkinglot.vehicle.presentation.controller.api.VehicleAdminApiUrl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import java.util.List;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Parking lot api"))
public class OpenApiConfiguration {

    private static final String WILDCARD = "/**";

    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Bean
    public List<GroupedOpenApi> groupedOpenApiList() {
        return List.of(managerApi(), integrationApi());
    }

    @Bean
    public GroupedOpenApi managerApi() {
        return GroupedOpenApi.builder()
            .group("parking-space-api")
            .pathsToMatch(
                ParkingSpaceAdminApiUrl.V1.ParkingSpace.BASE + WILDCARD
            )
            .build();
    }

    @Bean
    public GroupedOpenApi integrationApi() {
        return GroupedOpenApi.builder()
            .group("vehicle-api")
            .pathsToMatch(VehicleAdminApiUrl.V1.Vehicle.BASE + WILDCARD)
            .build();
    }

}
