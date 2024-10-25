CREATE TABLE review_medias
(
    id        SERIAL PRIMARY KEY,
    review_id INTEGER NOT NULL,
    media_url VARCHAR,

    CONSTRAINT fk_review_medias_review FOREIGN KEY (review_id) REFERENCES site_reviews (id)
);