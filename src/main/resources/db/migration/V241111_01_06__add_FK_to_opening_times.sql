ALTER TABLE opening_times
    ADD CONSTRAINT fk_opening_times_site_version
        FOREIGN KEY (site_version_id)
            REFERENCES site_versions (id);