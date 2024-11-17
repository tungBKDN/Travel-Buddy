CREATE TABLE travel_plans__users
(
    travel_plan_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    role VARCHAR(255) NOT NULL DEFAULT 'MEMBER' CHECK (role IN ('MEMBER', 'OWNER', 'ADMIN')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT NULL,

    CONSTRAINT fk_travel_plan_id FOREIGN KEY (travel_plan_id) REFERENCES travel_plans(id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id),

    CONSTRAINT pk_travel_plans__users PRIMARY KEY (travel_plan_id, user_id)
);