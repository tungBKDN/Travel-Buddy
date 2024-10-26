DO
$$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM permissions) THEN
            INSERT INTO permissions  (id, name)
            VALUES (1, 'MANAGE_USERS'),
                   (2, 'MANAGE_GROUPS'),
                   (3, 'MANAGE_PERMISSIONS'),
                   (4, 'MANAGE_ROLES'),
                   (5, 'MANAGE_CATEGORIES'),
                   (6, 'MANAGE_POSTS'),
                   (7, 'MANAGE_COMMENTS'),
                   (8, 'MANAGE_TAGS'),
                   (9, 'MANAGE_SITE_TYPES');
        END IF;
    END;
$$;

-- Reset sequence
SELECT setval('permissions_id_seq', (SELECT MAX(id) FROM permissions));