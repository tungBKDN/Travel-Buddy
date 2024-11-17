CREATE TABLE travel_plans__sites
(
    travel_plan_id INTEGER NOT NULL,
    site_id INTEGER NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    start_time TIMESTAMP DEFAULT NULL,
    end_time TIMESTAMP DEFAULT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT NULL,

    CONSTRAINT fk_travel_plan_id FOREIGN KEY (travel_plan_id) REFERENCES travel_plans(id),
    CONSTRAINT fk_site_id FOREIGN KEY (site_id) REFERENCES sites(id),

    CONSTRAINT pk_travel_plans__sites PRIMARY KEY (travel_plan_id, site_id)
);