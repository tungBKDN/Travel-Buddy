CREATE TABLE system_logs
(
    id        SERIAL PRIMARY KEY,
    timestamp TIMESTAMP NOT NULL,
    content   VARCHAR   NOT NULL
);