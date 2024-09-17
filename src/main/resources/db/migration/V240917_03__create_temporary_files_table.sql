START TRANSACTION ;

CREATE TABLE temporary_files(
    id VARCHAR(255) NOT NULL,
    upload_date TIMESTAMP NOT NULL,
    CONSTRAINT pk__temporary_files PRIMARY KEY (id)
);
CREATE INDEX idx__temporary_files__upload_date ON temporary_files USING BTREE (upload_date);

COMMIT ;