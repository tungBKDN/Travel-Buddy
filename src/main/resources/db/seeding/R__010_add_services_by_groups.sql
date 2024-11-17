DO
$$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM services_by_groups) THEN
            INSERT INTO services_by_groups (id, service_group_id, service_id)
            VALUES (1, 1, 1),
                   (2, 1, 2),
                   (3, 1, 3),
                   (4, 1, 4),
                   (5, 1, 5),
                   (6, 1, 6),
                   (7, 1, 7),
                   (8, 1, 8),
                   (9, 1, 9),
                   (10, 1, 10),
                   (11, 1, 11),
                   (12, 1, 12),
                   (13, 1, 13),
                   (14, 1, 14),
                   (15, 1, 15),
                   (16, 1, 16),
                   (17, 1, 17),
                   (18, 1, 18),
                   (19, 1, 19),
                   (20, 1, 20),

                   (21, 2, 21),
                   (22, 2, 22),
                   (23, 2, 23),
                   (24, 2, 24),
                   (25, 2, 25),
                   (26, 2, 26),
                   (27, 2, 27),
                   (28, 2, 28),
                   (29, 2, 29),
                   (30, 2, 30),
                   (31, 2, 31),
                   (32, 2, 32),
                   (33, 2, 33),
                   (34, 2, 34),
                   (35, 2, 35),

                   (36, 3, 36),
                   (37, 3, 37),
                   (38, 3, 38),
                   (39, 3, 39);

        END IF;
    END;
$$;

-- Reset sequence
SELECT setval('services_by_groups_id_seq', (SELECT MAX(id) FROM services_by_groups));