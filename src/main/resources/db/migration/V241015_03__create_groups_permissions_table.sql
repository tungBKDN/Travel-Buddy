CREATE TABLE groups_permissions (
    group_id INT NOT NULL,
    permission_id INT NOT NULL,
    CONSTRAINT pk__groups_permissions PRIMARY KEY (group_id, permission_id),
    CONSTRAINT fk__groups_permissions__group_id FOREIGN KEY (group_id) REFERENCES groups (id),
    CONSTRAINT fk__groups_permissions__permission_id FOREIGN KEY (permission_id) REFERENCES permissions (id)
);