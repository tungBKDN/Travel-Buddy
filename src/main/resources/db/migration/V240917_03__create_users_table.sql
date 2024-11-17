CREATE TABLE IF NOT EXISTS users
(
    id SERIAL NOT NULL,
    email        VARCHAR(255) NOT NULL,
    nickname    VARCHAR(255) NOT NULL,
    password     VARCHAR(255) NOT NULL,
    enabled      BOOLEAN      NOT NULL DEFAULT FALSE,
    full_name    VARCHAR(555) NOT NULL,
    gender      VARCHAR(50) CHECK (gender in ('MALE', 'FEMALE', 'OTHER')),
    phone_number VARCHAR(50),
    birth_date   DATE,
    address      VARCHAR(255),
    social_url   VARCHAR(255),
    score double precision default 0,
    created_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP,
    avatar_id  VARCHAR(255),

    CONSTRAINT pk__users PRIMARY KEY (id),
    CONSTRAINT uq__users__email UNIQUE (email),
    CONSTRAINT fk__users__avatar_id FOREIGN KEY (avatar_id) REFERENCES files(id)
);