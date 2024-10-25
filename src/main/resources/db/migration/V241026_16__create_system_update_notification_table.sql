CREATE TABLE system_update_notification
(
    id          SERIAL PRIMARY KEY,
    release_day DATE    NOT NULL,
    title       VARCHAR NOT NULL,
    content     TEXT    NOT NULL,
    created_by  INTEGER NOT NULL,

    CONSTRAINT fk_system_update_notification_user FOREIGN KEY (created_by) REFERENCES users (id)
);