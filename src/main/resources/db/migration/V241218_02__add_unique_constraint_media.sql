BEGIN;

ALTER TABLE site_medias
    ADD CONSTRAINT uq_site_medias_site_id_media_id UNIQUE (site_id, media_id);
ALTER TABLE review_medias
    ADD CONSTRAINT uq_review_medias_review_id_media_id UNIQUE (review_id, media_id);

COMMIT;