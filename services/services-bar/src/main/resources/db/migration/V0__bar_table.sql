CREATE TABLE bars
(
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL,
    geom    GEOGRAPHY(POINT, 4326) NOT NULL,
    open    TIME,
    close   TIME
);