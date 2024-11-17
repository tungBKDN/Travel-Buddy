CREATE TABLE travel_plans
(
    id        SERIAL PRIMARY KEY,
    name      VARCHAR(255) NOT NULL,
    description TEXT,
    start_time TIMESTAMP DEFAULT NULL,
    end_time TIMESTAMP DEFAULT NULL,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT NULL
);