DO
$$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM admins_groups) THEN
            INSERT INTO admins_groups (admin_id, group_id)
            VALUES  (1, 1);
        END IF;
    END;
$$;