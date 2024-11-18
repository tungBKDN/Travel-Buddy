

-- DROP CONSTRAINTS
ALTER TABLE fees DROP CONSTRAINT fk_fees_aspect;
ALTER TABLE aspects_by_type DROP CONSTRAINT fk_aspects_by_type_aspect;
ALTER TABLE aspects_by_type drop constraint fk_aspects_by_type_type;
ALTER TABLE detailed_ratings DROP CONSTRAINT fk_detailed_ratings_aspect;
-- DROP TABLES
DROP TABLE aspects;
-- CHANGE COLUMN OF ASPECT_ID INTO ASPECT, STORING THE ASPECT DATA AS A TEXT, REMOVE ABUNDANT TABLES
ALTER TABLE aspects_by_type DROP COLUMN aspect_id;
ALTER TABLE aspects_by_type ADD COLUMN aspect VARCHAR NOT NULL;
-- ADD FK CONSTRAINTS
ALTER TABLE fees ADD CONSTRAINT fk_fees_aspect FOREIGN KEY (aspect_id) REFERENCES aspects_by_type(id);
ALTER TABLE detailed_ratings ADD CONSTRAINT fk_detailed_ratings_aspect FOREIGN KEY (aspect_id) REFERENCES aspects_by_type(id);