CREATE TABLE restricted_history
(
    id                SERIAL PRIMARY KEY,
    user_id           INTEGER   NOT NULL,
    admin_id          INTEGER   NOT NULL,
    policy_applied_id INTEGER   NOT NULL,
    expiration_time   TIMESTAMP NOT NULL,

    CONSTRAINT fk_restricted_history_user FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_restricted_history_admin FOREIGN KEY (admin_id) REFERENCES users (id),
    CONSTRAINT fk_restricted_history_policy FOREIGN KEY (policy_applied_id) REFERENCES restriction_policy (id)
);