CREATE TABLE site_reactions
(
    id         SERIAL PRIMARY KEY,
    site_id  INTEGER       NOT NULL,
    user_id    INTEGER       NOT NULL,
    reaction_type VARCHAR CHECK (reaction_type IN ('LIKE', 'DISLIKE') OR reaction_type IS NULL) DEFAULT NULL,

    CONSTRAINT fk_site_reactions_review FOREIGN KEY (site_id) REFERENCES sites (id),
    CONSTRAINT fk_site_reactions_user FOREIGN KEY (user_id) REFERENCES users (id)
);