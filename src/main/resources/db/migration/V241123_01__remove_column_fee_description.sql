BEGIN;

ALTER TABLE fees
DROP COLUMN fee_description;

COMMIT;