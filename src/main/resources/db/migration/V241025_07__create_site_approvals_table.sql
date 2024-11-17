CREATE TABLE site_approvals
(
    id              SERIAL PRIMARY KEY,
    site_version_id INTEGER NOT NULL,
    status          approval_status,
    admin_id        INTEGER,
    approved_at     TIMESTAMP,

    CONSTRAINT fk_site_approvals_site_version FOREIGN KEY (site_version_id) REFERENCES site_versions (id),
    CONSTRAINT fk_site_approvals_admin FOREIGN KEY (admin_id) REFERENCES admins (id)
);