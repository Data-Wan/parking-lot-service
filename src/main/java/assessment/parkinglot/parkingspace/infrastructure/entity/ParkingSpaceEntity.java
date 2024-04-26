package assessment.parkinglot.parkingspace.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "parking_spaces", schema = "parking")
public class ParkingSpaceEntity {

    @Id
    private UUID id;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private ParkingSpaceType type;

    @Column(name = "is_available")
    private Boolean isAvailable;

    public enum ParkingSpaceType {
        COMPACT,
        MOTORCYCLE,
        REGULAR
    }
}
