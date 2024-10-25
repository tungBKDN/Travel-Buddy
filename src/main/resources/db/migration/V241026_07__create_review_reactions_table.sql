CREATE TABLE review_reactions
(
    id         SERIAL PRIMARY KEY,
    user_id    INTEGER       NOT NULL,
    review_id  INTEGER       NOT NULL,
    created_at TIMESTAMP     NOT NULL,
    type       reaction_type NOT NULL,

    CONSTRAINT fk_review_reactions_user FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_review_reactions_review FOREIGN KEY (review_id) REFERENCES site_reviews (id)
);