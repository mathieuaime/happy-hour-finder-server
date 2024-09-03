CREATE TABLE happy_hours
(
    id         SERIAL PRIMARY KEY,
    start_time TIME NOT NULL,
    end_time   TIME NOT NULL,
    bar_id     INT NOT NULL
);