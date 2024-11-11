CREATE TABLE service_groups_by_types
(
    id         SERIAL NOT NULL PRIMARY KEY,
    type_id    INTEGER NOT NULL,
    service_group_id INTEGER NOT NULL,

    CONSTRAINT fk_services_by_types_type FOREIGN KEY (type_id) REFERENCES site_types (id),
    CONSTRAINT fk_services_by_types_service_group FOREIGN KEY (service_group_id) REFERENCES service_groups (id)
);
