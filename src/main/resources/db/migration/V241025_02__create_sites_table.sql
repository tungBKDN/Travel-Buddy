CREATE TABLE sites
(
    id       SERIAL NOT NULL PRIMARY KEY,
    owner_id INTEGER NOT NULL,

    CONSTRAINT fk_sites_owner FOREIGN KEY (owner_id) REFERENCES users (id)
);