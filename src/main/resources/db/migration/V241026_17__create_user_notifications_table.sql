CREATE TABLE user_notifications
(
    id        SERIAL PRIMARY KEY,
    timestamp TIMESTAMP NOT NULL,
    title     VARCHAR   NOT NULL,
    content   TEXT      NOT NULL,
    media_url VARCHAR,
    user_id   INTEGER   NOT NULL,
    state     VARCHAR,

    CONSTRAINT fk_user_notifications_user FOREIGN KEY (user_id) REFERENCES users (id)
);