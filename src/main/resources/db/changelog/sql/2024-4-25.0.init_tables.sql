-- liquibase formatted sql
-- changeset v.ustimenko:init_tables
CREATE SCHEMA IF NOT EXISTS parking;

CREATE TABLE IF NOT EXISTS parking.parking_spaces
(
    id           UUID PRIMARY KEY,
    type         VARCHAR(255),
    is_available BOOLEAN
);

CREATE TABLE IF NOT EXISTS parking.vehicles
(
    id   UUID PRIMARY KEY,
    type VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS parking.vehicle_parking_spaces
(
    vehicle_id       UUID,
    parking_space_id UUID,
    PRIMARY KEY (vehicle_id, parking_space_id),
    CONSTRAINT fk__vehicle_parking_spaces__vehicle_id__vehicles_id
        FOREIGN KEY (vehicle_id)
            REFERENCES parking.vehicles (id),
    CONSTRAINT fk__vehicle_parking_spaces__parking_space_id__parking_spaces_id
        FOREIGN KEY (parking_space_id)
            REFERENCES parking.parking_spaces (id)
);
