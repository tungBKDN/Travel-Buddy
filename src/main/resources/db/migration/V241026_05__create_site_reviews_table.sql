CREATE TABLE site_reviews
(
    id             SERIAL PRIMARY KEY,
    site_id        INTEGER NOT NULL,
    comment        TEXT,
    general_rating INTEGER,

    CONSTRAINT fk_site_reviews_site FOREIGN KEY (site_id) REFERENCES sites (id)
);
