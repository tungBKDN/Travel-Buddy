CREATE TABLE opening_times
(
    id              SERIAL PRIMARY KEY,
    site_version_id INTEGER NOT NULL,
    day_of_week     day_of_week,
    open_time       TIME,
    close_time      TIME
);