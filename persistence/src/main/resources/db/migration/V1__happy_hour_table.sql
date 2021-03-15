CREATE TABLE IF NOT EXISTS happy_hour
(
    id     VARCHAR(36) PRIMARY KEY NOT NULL,
    start  DATE,
    end    DATE,
    bar_id VARCHAR(36)
);