CREATE TABLE IF NOT EXISTS favourites
(
    id
    BIGINT
    AUTO_INCREMENT
    PRIMARY
    KEY,
    location_name
    VARCHAR
(
    255
) NOT NULL,
    country VARCHAR
(
    100
),
    latitude DOUBLE,
    longitude DOUBLE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );
