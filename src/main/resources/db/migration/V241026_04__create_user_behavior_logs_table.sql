CREATE TABLE user_behavior_logs
(
    id              SERIAL PRIMARY KEY,
    timestamp       TIMESTAMP NOT NULL,
    user_id         INTEGER   NOT NULL,
    extra_info      VARCHAR,
    behavior_id     INTEGER   NOT NULL,
    site_version_id INTEGER,

    CONSTRAINT fk_user_behavior_logs_user FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_user_behavior_logs_behavior FOREIGN KEY (behavior_id) REFERENCES user_behaviors (id)
);