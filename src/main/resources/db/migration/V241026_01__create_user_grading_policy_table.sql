CREATE TABLE user_grading_policy
(
    id          SERIAL PRIMARY KEY,
    action_name VARCHAR NOT NULL,
    description TEXT,
    pize_point  INTEGER NOT NULL
);