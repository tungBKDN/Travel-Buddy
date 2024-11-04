CREATE TABLE services_by_groups
(
    id               SERIAL PRIMARY KEY,
    service_id       INTEGER NOT NULL,
    service_group_id INTEGER NOT NULL,

    CONSTRAINT fk_services_by_groups_service FOREIGN KEY (service_id) REFERENCES services (id),
    CONSTRAINT fk_services_by_groups_service_group FOREIGN KEY (service_group_id) REFERENCES service_groups (id)
);