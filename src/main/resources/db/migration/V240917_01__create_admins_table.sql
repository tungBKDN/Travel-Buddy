CREATE TABLE IF NOT EXISTS admins
(
    id SERIAL NOT NULL,
    username     VARCHAR(255),
    email        VARCHAR(255) NOT NULL,
    password     VARCHAR(255) NOT NULL,
    role         SMALLINT         NOT NULL,
    enabled      BOOLEAN      NOT NULL DEFAULT TRUE,
    full_name    VARCHAR(555) NOT NULL,
    created_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    token        VARCHAR(255),

    CONSTRAINT pk__admins PRIMARY KEY (id),
    CONSTRAINT uq__admins__email UNIQUE (email),
    CONSTRAINT chk__admins__role CHECK (role BETWEEN 1 AND 3)
);