CREATE TABLE grading_logs
(
    id         SERIAL PRIMARY KEY,
    user_id    INTEGER NOT NULL,
    grading_id INTEGER NOT NULL,

    CONSTRAINT fk_grading_logs_user FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_grading_logs_policy FOREIGN KEY (grading_id) REFERENCES user_grading_policy (id)
);