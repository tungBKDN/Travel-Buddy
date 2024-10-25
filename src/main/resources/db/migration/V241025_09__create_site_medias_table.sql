CREATE TABLE site_medias
(
    id              SERIAL PRIMARY KEY,
    site_version_id INTEGER NOT NULL,
    media_url       VARCHAR NOT NULL,

    CONSTRAINT fk_site_medias_site_version FOREIGN KEY (site_version_id) REFERENCES site_versions (id)
);