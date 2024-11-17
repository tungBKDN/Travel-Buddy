CREATE TABLE review_reactions
(
    id         SERIAL PRIMARY KEY,
    review_id  INTEGER       NOT NULL,
    user_id    INTEGER       NOT NULL,
    reaction_type VARCHAR CHECK (reaction_type IN ('LIKE', 'DISLIKE') OR reaction_type IS NULL) DEFAULT NULL,

    CONSTRAINT fk_review_reactions_review FOREIGN KEY (review_id) REFERENCES site_reviews (id),
    CONSTRAINT fk_review_reactions_user FOREIGN KEY (user_id) REFERENCES users (id)
);