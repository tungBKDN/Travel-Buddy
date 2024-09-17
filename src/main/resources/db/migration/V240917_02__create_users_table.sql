CREATE TABLE IF NOT EXISTS users
(
    id SERIAL NOT NULL,
    username     VARCHAR(255),
    email        VARCHAR(255) NOT NULL,
    password     VARCHAR(255) NOT NULL,
    enabled      BOOLEAN      NOT NULL DEFAULT TRUE,
    full_name    VARCHAR(555) NOT NULL,
    created_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    token        VARCHAR(255),

    CONSTRAINT pk__users PRIMARY KEY (id),
    CONSTRAINT uq__users__email UNIQUE (email)
);