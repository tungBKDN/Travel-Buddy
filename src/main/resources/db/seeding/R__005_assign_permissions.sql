DO
$$
    BEGIN
        INSERT INTO groups_permissions (group_id, permission_id)
        VALUES  (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9),
                (2, 5), (2, 6), (2, 7), (2, 8), (2, 9);
    END;
$$;