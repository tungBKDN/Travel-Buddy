CREATE TABLE restriction_policy
(
    id               SERIAL NOT NULL PRIMARY KEY,
    policy_name      VARCHAR NOT NULL,
    description      TEXT    NOT NULL,
    ban_length       BIGINT  NOT NULL,
    target           target,
    is_hiding_targer BOOLEAN NOT NULL,
    is_delete_targer BOOLEAN NOT NULL,
    cannot_post      BOOLEAN NOT NULL,
    cannot_review    BOOLEAN NOT NULL,
    cannot_edit      BOOLEAN NOT NULL
);