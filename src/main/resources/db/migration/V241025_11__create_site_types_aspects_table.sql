CREATE TABLE aspects_by_type
(
    id        SERIAL PRIMARY KEY,
    type_id   INTEGER NOT NULL,
    aspect_id INTEGER NOT NULL,

    CONSTRAINT fk_aspects_by_type_type FOREIGN KEY (type_id) REFERENCES site_types (id),
    CONSTRAINT fk_aspects_by_type_aspect FOREIGN KEY (aspect_id) REFERENCES aspects (id)
);