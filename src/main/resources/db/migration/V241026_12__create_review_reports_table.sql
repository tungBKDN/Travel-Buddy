CREATE TABLE review_reports
(
    id        SERIAL PRIMARY KEY,
    report_id INTEGER NOT NULL,
    review_id INTEGER NOT NULL,

    CONSTRAINT fk_review_reports_report FOREIGN KEY (report_id) REFERENCES reports (id),
    CONSTRAINT fk_review_reports_review FOREIGN KEY (review_id) REFERENCES site_reviews (id)
);