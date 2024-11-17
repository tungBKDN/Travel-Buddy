CREATE TABLE IF NOT EXISTS site_reviews
(
    id             SERIAL NOT NULL PRIMARY KEY,
    site_id        INTEGER NOT NULL,
    user_id        INTEGER NOT NULL,
    comment        TEXT,
    general_rating INTEGER NOT NULL,
    arrival_date DATE NOT NULL,
    created_at     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at     TIMESTAMP DEFAULT NULL,

    CONSTRAINT uq_site_reviews_site_user UNIQUE (site_id, user_id),
    CONSTRAINT fk_site_reviews_site FOREIGN KEY (site_id) REFERENCES sites (id),
    CONSTRAINT fk_site_reviews_user FOREIGN KEY (user_id) REFERENCES users (id)
);
