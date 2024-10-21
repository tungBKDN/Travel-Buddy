DO
$$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM users) THEN
            INSERT INTO users (id, username, email, password, enabled, full_name, created_at, updated_at)
            VALUES  (1, 'user', 'user@localhost', '$2a$12$TjXthC5BYhGKIr3BpT42eeK7OWCpfiPhZ9bkO/PFGsvfAp.u9/Ol6', TRUE, 'User', NOW(), NOW()),
                    (2, 'user2', 'user2', '$2a$12$TjXthC5BYhGKIr3BpT42eeK7OWCpfiPhZ9bkO/PFGsvfAp.u9/Ol6', TRUE, 'User 2', NOW(), NOW()),
                    (3, 'user3', 'user3', '$2a$12$TjXthC5BYhGKIr3BpT42eeK7OWCpfiPhZ9bkO/PFGsvfAp.u9/Ol6', TRUE, 'User 3', NOW(), NOW());
        END IF;
    END;
$$;