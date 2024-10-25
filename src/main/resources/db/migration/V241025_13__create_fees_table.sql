CREATE TABLE fees
(
    id              SERIAL PRIMARY KEY,
    site_version_id INTEGER NOT NULL,
    aspect_id       INTEGER NOT NULL,
    fee_description VARCHAR,
    fee_low         INTEGER,
    fee_high        INTEGER,

    CONSTRAINT fk_fees_site_version FOREIGN KEY (site_version_id) REFERENCES site_versions (id),
    CONSTRAINT fk_fees_aspect FOREIGN KEY (aspect_id) REFERENCES aspects (id)
);