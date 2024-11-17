CREATE TABLE services_by_types
(
    id         SERIAL NOT NULL PRIMARY KEY,
    type_id    INTEGER NOT NULL,
    service_id INTEGER NOT NULL,

    CONSTRAINT fk_services_by_types_type FOREIGN KEY (type_id) REFERENCES site_types (id),
    CONSTRAINT fk_services_by_types_service FOREIGN KEY (service_id) REFERENCES services (id)
);
