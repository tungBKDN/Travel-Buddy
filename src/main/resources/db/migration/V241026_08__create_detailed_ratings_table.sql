CREATE TABLE detailed_ratings
(
    id        SERIAL PRIMARY KEY,
    review_id INTEGER NOT NULL,
    aspect_id INTEGER NOT NULL,
    rating    INTEGER,

    CONSTRAINT fk_detailed_ratings_review FOREIGN KEY (review_id) REFERENCES site_reviews (id),
    CONSTRAINT fk_detailed_ratings_aspect FOREIGN KEY (aspect_id) REFERENCES aspects (id)
);