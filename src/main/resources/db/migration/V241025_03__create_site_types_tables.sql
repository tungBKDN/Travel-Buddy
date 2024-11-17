CREATE TABLE site_types
(
    id         SERIAL NOT NULL PRIMARY KEY,
    type_name  VARCHAR(255) NOT NULL,
    dual_state dual_state NOT NULL
);