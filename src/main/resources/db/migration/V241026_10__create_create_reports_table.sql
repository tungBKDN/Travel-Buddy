CREATE TABLE reports
(
    id           SERIAL PRIMARY KEY,
    user_id      INTEGER     NOT NULL,
    reason_id    INTEGER     NOT NULL,
    explaination TEXT,
    type         report_type NOT NULL,

    CONSTRAINT fk_reports_user FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_reports_reason FOREIGN KEY (reason_id) REFERENCES report_reasons (id)
);