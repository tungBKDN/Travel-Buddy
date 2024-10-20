START TRANSACTION;

-- Define ENUM types
CREATE TYPE dual_state AS ENUM ('ATTRACTION', 'UTILITY', 'DUAL');
CREATE TYPE approval_status AS ENUM ('APPROVED', 'REJECTED');
CREATE TYPE day_of_week AS ENUM ('MO', 'TU', 'WE', 'TH', 'FR', 'SA', 'SU');
CREATE TYPE report_type AS ENUM ('SITE', 'REVIEW');
CREATE TYPE reaction_type AS ENUM ('LIKE', 'DISLIKE');
CREATE TYPE target AS ENUM ('USER', 'SITE');
CREATE TYPE user_class AS ENUM ('USER', 'ADMIN');

-- Table Definitions
-- CREATE TABLE users (
--                        id SERIAL PRIMARY KEY,
--                        username VARCHAR NOT NULL,
--                        email VARCHAR NOT NULL,
--                        password VARCHAR NOT NULL,
--                        sign_up_at TIMESTAMP NOT NULL,
--                        token VARCHAR,
--                        profile_picture VARCHAR
-- );

CREATE TABLE sites
(
    id       SERIAL PRIMARY KEY,
    owner_id INTEGER NOT NULL
);

CREATE TABLE site_types
(
    id         SERIAL PRIMARY KEY,
    type_name  VARCHAR,
    dual_state dual_state
);

CREATE TABLE services
(
    id           SERIAL PRIMARY KEY,
    service_name VARCHAR NOT NULL
);

CREATE TABLE services_by_types
(
    id         SERIAL PRIMARY KEY,
    type_id    INTEGER NOT NULL,
    service_id INTEGER NOT NULL
);

CREATE TABLE site_versions
(
    id                SERIAL PRIMARY KEY,
    site_id           INTEGER          NOT NULL,
    parent_version_id INTEGER,
    site_name         VARCHAR          NOT NULL,
    lat               DOUBLE PRECISION NOT NULL,
    lon               DOUBLE PRECISION NOT NULL,
    resolved_address  VARCHAR,
    website           VARCHAR,
    created_at        TIMESTAMP,
    type_id           INTEGER
);

CREATE TABLE site_approvals
(
    id              SERIAL PRIMARY KEY,
    site_version_id INTEGER NOT NULL,
    status          approval_status,
    admin_id        INTEGER,
    approved_at     TIMESTAMP
);

CREATE TABLE phone_numbers
(
    id               SERIAL PRIMARY KEY,
    site_versions_id INTEGER NOT NULL,
    phone_number     VARCHAR NOT NULL
);

CREATE TABLE site_medias
(
    id              SERIAL PRIMARY KEY,
    site_version_id INTEGER NOT NULL,
    media_url       VARCHAR NOT NULL
);

CREATE TABLE aspects_by_type
(
    id        SERIAL PRIMARY KEY,
    type_id   INTEGER NOT NULL,
    aspect_id INTEGER NOT NULL
);

CREATE TABLE aspects
(
    id          SERIAL PRIMARY KEY,
    aspect_name VARCHAR NOT NULL
);

CREATE TABLE opening_times
(
    id              SERIAL PRIMARY KEY,
    site_version_id INTEGER NOT NULL,
    day_of_week     day_of_week,
    open_time       TIME,
    close_time      TIME
);

CREATE TABLE fees
(
    id              SERIAL PRIMARY KEY,
    site_version_id INTEGER NOT NULL,
    aspect_id       INTEGER NOT NULL,
    fee_description VARCHAR,
    fee_low         INTEGER,
    fee_high        INTEGER
);

CREATE TABLE user_grading_policy
(
    id          SERIAL PRIMARY KEY,
    action_name VARCHAR NOT NULL,
    description TEXT,
    pize_point  INTEGER NOT NULL
);

CREATE TABLE grading_logs
(
    id         SERIAL PRIMARY KEY,
    user_id    INTEGER NOT NULL,
    grading_id INTEGER NOT NULL
);

CREATE TABLE user_behavior_logs
(
    id              SERIAL PRIMARY KEY,
    timestamp       TIMESTAMP NOT NULL,
    user_id         INTEGER   NOT NULL,
    extra_info      VARCHAR,
    behavior_id     INTEGER   NOT NULL,
    site_version_id INTEGER
);

CREATE TABLE user_behaviors
(
    id            SERIAL PRIMARY KEY,
    behavior_name VARCHAR NOT NULL,
    description   TEXT
);

-- Reviews
CREATE TABLE site_reviews
(
    id             SERIAL PRIMARY KEY,
    site_id        INTEGER NOT NULL,
    comment        TEXT,
    general_rating INTEGER
);

CREATE TABLE review_medias
(
    id        SERIAL PRIMARY KEY,
    review_id INTEGER NOT NULL,
    media_url VARCHAR
);

CREATE TABLE review_reactions
(
    id         SERIAL PRIMARY KEY,
    user_id    INTEGER       NOT NULL,
    review_id  INTEGER       NOT NULL,
    created_at TIMESTAMP     NOT NULL,
    type       reaction_type NOT NULL
);

CREATE TABLE detailed_ratings
(
    id        SERIAL PRIMARY KEY,
    review_id INTEGER NOT NULL,
    aspect_id INTEGER NOT NULL,
    rating    INTEGER
);

-- Reports
CREATE TABLE report_reasons
(
    id     SERIAL PRIMARY KEY,
    reason VARCHAR
);

CREATE TABLE reports
(
    id           SERIAL PRIMARY KEY,
    user_id      INTEGER     NOT NULL,
    reason_id    INTEGER     NOT NULL,
    explaination TEXT,
    type         report_type NOT NULL
);

CREATE TABLE site_reports
(
    id              SERIAL PRIMARY KEY,
    report_id       INTEGER NOT NULL,
    site_version_id INTEGER NOT NULL
);

CREATE TABLE review_reports
(
    id        SERIAL PRIMARY KEY,
    report_id INTEGER NOT NULL,
    review_id INTEGER NOT NULL
);

CREATE TABLE restriction_policy
(
    id               SERIAL PRIMARY KEY,
    policy_name      VARCHAR NOT NULL,
    description      TEXT    NOT NULL,
    ban_length       BIGINT  NOT NULL,
    target           target,
    is_hiding_targer BOOLEAN NOT NULL,
    is_delete_targer BOOLEAN NOT NULL,
    cannot_post      BOOLEAN NOT NULL,
    cannot_review    BOOLEAN NOT NULL,
    cannot_edit      BOOLEAN NOT NULL
);

CREATE TABLE restricted_history
(
    id                SERIAL PRIMARY KEY,
    user_id           INTEGER   NOT NULL,
    admin_id          INTEGER   NOT NULL,
    policy_applied_id INTEGER   NOT NULL,
    expiration_time   TIMESTAMP NOT NULL
);

CREATE TABLE system_logs
(
    id        SERIAL PRIMARY KEY,
    timestamp TIMESTAMP NOT NULL,
    content   VARCHAR   NOT NULL
);

CREATE TABLE system_update_notification
(
    id          SERIAL PRIMARY KEY,
    release_day DATE    NOT NULL,
    title       VARCHAR NOT NULL,
    content     TEXT    NOT NULL,
    created_by  INTEGER NOT NULL
);

CREATE TABLE user_notifications
(
    id        SERIAL PRIMARY KEY,
    timestamp TIMESTAMP NOT NULL,
    title     VARCHAR   NOT NULL,
    content   TEXT      NOT NULL,
    media_url VARCHAR,
    user_id   INTEGER   NOT NULL,
    state     VARCHAR
);

CREATE TABLE permissions
(
    id               SERIAL PRIMARY KEY,
    permission_title VARCHAR NOT NULL,
    description      TEXT    NOT NULL,
    class            user_class
);

CREATE TABLE user_permissions
(
    id            SERIAL PRIMARY KEY,
    user_id       INTEGER NOT NULL,
    permission_id INTEGER NOT NULL
);

-- Foreign Key Constraints
-- Foreign Key Constraints
ALTER TABLE site_approvals
    ADD CONSTRAINT fk_site_approvals_site_version FOREIGN KEY (site_version_id) REFERENCES site_versions (id);
ALTER TABLE site_approvals
    ADD CONSTRAINT fk_site_approvals_admin FOREIGN KEY (admin_id) REFERENCES users (id);
ALTER TABLE sites
    ADD CONSTRAINT fk_sites_owner FOREIGN KEY (owner_id) REFERENCES users (id);
ALTER TABLE services_by_types
    ADD CONSTRAINT fk_services_by_types_type FOREIGN KEY (type_id) REFERENCES site_types (id);
ALTER TABLE services_by_types
    ADD CONSTRAINT fk_services_by_types_service FOREIGN KEY (service_id) REFERENCES services (id);
ALTER TABLE site_versions
    ADD CONSTRAINT fk_site_versions_site FOREIGN KEY (site_id) REFERENCES sites (id);
ALTER TABLE site_versions
    ADD CONSTRAINT fk_site_versions_type FOREIGN KEY (type_id) REFERENCES site_types (id);
ALTER TABLE phone_numbers
    ADD CONSTRAINT fk_phone_numbers_site_versions FOREIGN KEY (site_versions_id) REFERENCES site_versions (id);
ALTER TABLE site_medias
    ADD CONSTRAINT fk_site_medias_site_version FOREIGN KEY (site_version_id) REFERENCES site_versions (id);
ALTER TABLE aspects_by_type
    ADD CONSTRAINT fk_aspects_by_type_type FOREIGN KEY (type_id) REFERENCES site_types (id);
ALTER TABLE aspects_by_type
    ADD CONSTRAINT fk_aspects_by_type_aspect FOREIGN KEY (aspect_id) REFERENCES aspects (id);
ALTER TABLE fees
    ADD CONSTRAINT fk_fees_site_version FOREIGN KEY (site_version_id) REFERENCES site_versions (id);
ALTER TABLE fees
    ADD CONSTRAINT fk_fees_aspect FOREIGN KEY (aspect_id) REFERENCES aspects (id);
ALTER TABLE grading_logs
    ADD CONSTRAINT fk_grading_logs_user FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE grading_logs
    ADD CONSTRAINT fk_grading_logs_policy FOREIGN KEY (grading_id) REFERENCES user_grading_policy (id);
ALTER TABLE user_behavior_logs
    ADD CONSTRAINT fk_user_behavior_logs_user FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE user_behavior_logs
    ADD CONSTRAINT fk_user_behavior_logs_behavior FOREIGN KEY (behavior_id) REFERENCES user_behaviors (id);
ALTER TABLE site_reviews
    ADD CONSTRAINT fk_site_reviews_site FOREIGN KEY (site_id) REFERENCES sites (id);
ALTER TABLE review_medias
    ADD CONSTRAINT fk_review_medias_review FOREIGN KEY (review_id) REFERENCES site_reviews (id);
ALTER TABLE review_reactions
    ADD CONSTRAINT fk_review_reactions_user FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE review_reactions
    ADD CONSTRAINT fk_review_reactions_review FOREIGN KEY (review_id) REFERENCES site_reviews (id);
ALTER TABLE detailed_ratings
    ADD CONSTRAINT fk_detailed_ratings_review FOREIGN KEY (review_id) REFERENCES site_reviews (id);
ALTER TABLE detailed_ratings
    ADD CONSTRAINT fk_detailed_ratings_aspect FOREIGN KEY (aspect_id) REFERENCES aspects (id);
ALTER TABLE reports
    ADD CONSTRAINT fk_reports_user FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE reports
    ADD CONSTRAINT fk_reports_reason FOREIGN KEY (reason_id) REFERENCES report_reasons (id);
ALTER TABLE site_reports
    ADD CONSTRAINT fk_site_reports_report FOREIGN KEY (report_id) REFERENCES reports (id);
ALTER TABLE site_reports
    ADD CONSTRAINT fk_site_reports_site_version FOREIGN KEY (site_version_id) REFERENCES site_versions (id);
ALTER TABLE review_reports
    ADD CONSTRAINT fk_review_reports_report FOREIGN KEY (report_id) REFERENCES reports (id);
ALTER TABLE review_reports
    ADD CONSTRAINT fk_review_reports_review FOREIGN KEY (review_id) REFERENCES site_reviews (id);
ALTER TABLE restricted_history
    ADD CONSTRAINT fk_restricted_history_user FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE restricted_history
    ADD CONSTRAINT fk_restricted_history_admin FOREIGN KEY (admin_id) REFERENCES users (id);
ALTER TABLE restricted_history
    ADD CONSTRAINT fk_restricted_history_policy FOREIGN KEY (policy_applied_id) REFERENCES restriction_policy (id);
ALTER TABLE user_permissions
    ADD CONSTRAINT fk_user_permissions_user FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE user_permissions
    ADD CONSTRAINT fk_user_permissions_permission FOREIGN KEY (permission_id) REFERENCES permissions (id);
ALTER TABLE system_update_notification
    ADD CONSTRAINT fk_system_update_notification_user FOREIGN KEY (created_by) REFERENCES users (id);
ALTER TABLE user_notifications
    ADD CONSTRAINT fk_user_notifications_user FOREIGN KEY (user_id) REFERENCES users (id);

COMMIT;