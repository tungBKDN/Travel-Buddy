BEGIN;

ALTER TABLE site_versions
ADD COLUMN site_description text;

COMMIT;