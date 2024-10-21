CREATE TABLE admins_groups (
    admin_id INT NOT NULL,
    group_id INT NOT NULL,
    CONSTRAINT pk__admins_groups PRIMARY KEY (admin_id, group_id),
    CONSTRAINT fk__admins_groups__admin_id FOREIGN KEY (admin_id) REFERENCES admins (id),
    CONSTRAINT fk__admins_groups__group_id FOREIGN KEY (group_id) REFERENCES groups (id)
);