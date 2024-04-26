-- liquibase formatted sql
-- changeset v.ustimenko:insert_default_values

-- Insert parking spaces
INSERT INTO parking.parking_spaces (id, type, is_available)
VALUES
    ('d54d4c34-e63b-4d54-9fe9-4fc0d4ef404d', 'MOTORCYCLE', 'true'),
    ('d54d4c34-e63b-4d54-9fe9-4fc0d4ef403d', 'MOTORCYCLE', 'true'),
    ('d54d4c34-e63b-4d54-9fe9-4fc0d4ef402d', 'MOTORCYCLE', 'true'),
    ('d54d4c34-e63b-4d54-9fe9-4fc0d4ef401d', 'MOTORCYCLE', 'true'),
    ('d54d4c34-e63b-4d54-9fe9-4fc0d4ef400d', 'MOTORCYCLE', 'true'),
    ('d54d4c34-e63b-4d54-9fe9-4fc0d4ef399d', 'COMPACT', 'true'),
    ('d54d4c34-e63b-4d54-9fe9-4fc0d4ef394d', 'COMPACT', 'true'),
    ('d54d4c34-e63b-4d54-9fe9-4fc0d4ef391d', 'COMPACT', 'true'),
    ('d54d4c34-e63b-4d54-9fe9-4fc0d4ef432d', 'COMPACT', 'true'),
    ('d54d4c34-e63b-4d54-9fe9-4fc0d4ef122d', 'COMPACT', 'true'),
    ('d54d4c34-e63b-4d54-9fe9-4fc0d4ef126d', 'REGULAR', 'true'),
    ('d54d4c34-e63b-4d54-9fe9-4fc0d4ef186d', 'REGULAR', 'true'),
    ('d54d4c34-e63b-4d54-9fe9-4fc0d4ef197d', 'REGULAR', 'true'),
    ('d54d4c34-e63b-4d54-9fe9-4fc0d4ef167d', 'REGULAR', 'true'),
    ('d54d4c34-e63b-4d54-9fe9-4fc0d4ef975d', 'REGULAR', 'true'),
    ('d54d4c34-e63b-4d54-9fe9-4fc0d4ef645d', 'REGULAR', 'true'),
    ('d54d4c34-e63b-4d54-9fe9-4fc0d4ef874d', 'REGULAR', 'true'),
    ('d54d4c34-e63b-4d54-9fe9-4fc0d4ef567d', 'REGULAR', 'true'),
    ('d54d4c34-e63b-4d54-9fe9-4fc0d4ef896d', 'REGULAR', 'true'),
    ('d54d4c34-e63b-4d54-9fe9-4fc0d4ef190d', 'REGULAR', 'true'),
    ('d54d4c34-e63b-4d54-9fe9-4fc0d4ef185d', 'REGULAR', 'true'),
    ('d54d4c34-e63b-4d54-9fe9-4fc0d4ef183d', 'REGULAR', 'true'),
    ('d54d4c34-e63b-4d54-9fe9-4fc0d4ef156d', 'REGULAR', 'true'),
    ('d54d4c34-e63b-4d54-9fe9-4fc0d4ef143d', 'REGULAR', 'true'),
    ('d54d4c34-e63b-4d54-9fe9-4fc0d4ef123d', 'REGULAR', 'true');
