CREATE TABLE site_versions
(
    id                SERIAL PRIMARY KEY,
    site_id           INTEGER          NOT NULL,
    parent_version_id INTEGER,
    site_name         VARCHAR          NOT NULL,
    lat               DOUBLE PRECISION NOT NULL,
    lng               DOUBLE PRECISION NOT NULL,
    resolved_address  VARCHAR,
    website           VARCHAR,
    created_at        TIMESTAMP,
    type_id           INTEGER,

    CONSTRAINT fk_site_versions_site FOREIGN KEY (site_id) REFERENCES sites (id),
    CONSTRAINT fk_site_versions_type FOREIGN KEY (type_id) REFERENCES site_types (id)
);