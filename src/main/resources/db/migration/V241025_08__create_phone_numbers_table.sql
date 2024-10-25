CREATE TABLE phone_numbers
(
    id               SERIAL NOT NULL PRIMARY KEY,
    site_versions_id INTEGER NOT NULL,
    phone_number     VARCHAR NOT NULL,

    CONSTRAINT fk_phone_numbers_site_versions FOREIGN KEY (site_versions_id) REFERENCES site_versions (id)
);