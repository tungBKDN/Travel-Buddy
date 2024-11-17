CREATE TABLE IF NOT EXISTS admins
(
    id SERIAL NOT NULL,
    email        VARCHAR(255) NOT NULL,
    nickname    VARCHAR(255),
    password     VARCHAR(255) NOT NULL,
    enabled      BOOLEAN      NOT NULL DEFAULT FALSE,
    full_name    VARCHAR(555) NOT NULL,
    gender      VARCHAR(50) CHECK (gender in ('MALE', 'FEMALE', 'OTHER')),
    phone_number VARCHAR(50),
    birth_date   DATE,
    address      VARCHAR(255),
    social_url  VARCHAR(255),
    created_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP,
    avatar_id   VARCHAR(255),

    CONSTRAINT pk__admins PRIMARY KEY (id),
    CONSTRAINT uq__admins__email UNIQUE (email),
    CONSTRAINT fk__admins__avatar_id FOREIGN KEY (avatar_id) REFERENCES files(id)
);