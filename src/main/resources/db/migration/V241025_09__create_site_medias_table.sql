CREATE TABLE IF NOT EXISTS site_medias
(
    id        SERIAL NOT NULL PRIMARY KEY,
    site_id INTEGER NOT NULL,
    media_id VARCHAR NOT NULL,
    media_type     VARCHAR NOT NULL CHECK (media_type IN ('IMAGE', 'VIDEO')),

    CONSTRAINT fk_site_medias_site FOREIGN KEY (site_id) REFERENCES sites (id),
    CONSTRAINT fk_site_medias_media FOREIGN KEY (media_id) REFERENCES files(id)
    );