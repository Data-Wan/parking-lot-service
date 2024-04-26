package assessment.parkinglot.vehicle.infrastructure.entity;

import assessment.parkinglot.parkingspace.infrastructure.entity.ParkingSpaceEntity;
import assessment.parkinglot.vehicle.application.dto.VehicleDto;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "vehicles", schema = "parking")
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(value = EnumType.STRING)
    private VehicleType type;

    @ManyToMany
    @JoinTable(
        name = "vehicle_parking_spaces",
        schema = "parking",
        joinColumns = @JoinColumn(name = "vehicle_id"),
        inverseJoinColumns = @JoinColumn(name = "parking_space_id")
    )
    private List<ParkingSpaceEntity> parkingSpaces;

    public enum VehicleType {
        CAR,
        MOTORCYCLE,
        VAN
    }
}
