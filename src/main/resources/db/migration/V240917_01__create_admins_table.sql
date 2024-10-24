CREATE TABLE IF NOT EXISTS admins
(
    id SERIAL NOT NULL,
    username     VARCHAR(255),
    email        VARCHAR(255) NOT NULL,
    password     VARCHAR(255) NOT NULL,
    enabled      BOOLEAN      NOT NULL DEFAULT FALSE,
    full_name    VARCHAR(555) NOT NULL,
    created_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    profile_picture VARCHAR(255),
    token        VARCHAR(255),

    CONSTRAINT pk__admins PRIMARY KEY (id),
    CONSTRAINT uq__admins__email UNIQUE (email)
);