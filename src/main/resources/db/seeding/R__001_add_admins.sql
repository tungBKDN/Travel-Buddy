DO
$$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM admins) THEN
            INSERT INTO admins (id, nickname, password, email, enabled, full_name, created_at, updated_at)
            VALUES  (1, 'admin', '$2a$12$TjXthC5BYhGKIr3BpT42eeK7OWCpfiPhZ9bkO/PFGsvfAp.u9/Ol6', 'admin@localhost', TRUE, 'Admin', NOW(), NOW()),
                    (2, 'superadmin', '$2a$12$TjXthC5BYhGKIr3BpT42eeK7OWCpfiPhZ9bkO/PFGsvfAp.u9/Ol6', 'superadmin@localhost', TRUE, 'Super Admin', NOW(), NOW()),
                    (3, 'contentadmin', '$2a$12$TjXthC5BYhGKIr3BpT42eeK7OWCpfiPhZ9bkO/PFGsvfAp.u9/Ol6', 'contentadmin@localhost', TRUE, 'Content Admin', NOW(), NOW());
        END IF;
    END;
$$;

-- Reset sequence
SELECT setval('admins_id_seq', (SELECT MAX(id) FROM admins));