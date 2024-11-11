CREATE TABLE services_by_site_versions
(
    id              SERIAL  NOT NULL PRIMARY KEY,
    site_version_id INTEGER NOT NULL,
    service_id      INTEGER NOT NULL,

    CONSTRAINT fk_services_by_site_versions_site_version_id FOREIGN KEY (site_version_id) REFERENCES site_versions (id),
    CONSTRAINT fk_services_by_site_versions_service_id FOREIGN KEY (service_id) REFERENCES services (id)
);
