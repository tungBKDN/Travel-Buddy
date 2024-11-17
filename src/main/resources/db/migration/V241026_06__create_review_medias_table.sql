CREATE TABLE IF NOT EXISTS review_medias
(
    id        SERIAL NOT NULL PRIMARY KEY,
    review_id INTEGER NOT NULL,
    media_id VARCHAR NOT NULL,
    media_type     VARCHAR NOT NULL CHECK (media_type IN ('IMAGE', 'VIDEO')),

    CONSTRAINT fk_review_medias_review FOREIGN KEY (review_id) REFERENCES site_reviews (id),
    CONSTRAINT fk_review_medias_media FOREIGN KEY (media_id) REFERENCES files (id)
);