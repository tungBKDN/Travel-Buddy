BEGIN;

ALTER TABLE travel_plans ADD COLUMN cover_id VARCHAR(255);
ALTER TABLE travel_plans ADD CONSTRAINT fk__travel_plans__cover_id FOREIGN KEY (cover_id) REFERENCES files(id);

COMMIT;