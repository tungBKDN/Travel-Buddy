DO
$$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM groups) THEN
            INSERT INTO groups (id, name)
            VALUES  (1, 'SUPER_ADMIN'),
                    (2, 'CONTENT_MANAGER');
        END IF;
    END;
$$;