CREATE TABLE site_reports
(
    id              SERIAL PRIMARY KEY,
    report_id       INTEGER NOT NULL,
    site_version_id INTEGER NOT NULL,

    CONSTRAINT fk_site_reports_report FOREIGN KEY (report_id) REFERENCES reports (id),
    CONSTRAINT fk_site_reports_site_version FOREIGN KEY (site_version_id) REFERENCES site_versions (id)
);