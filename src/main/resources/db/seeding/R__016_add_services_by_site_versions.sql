DO
$$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM services_by_site_versions) THEN
            INSERT INTO services_by_site_versions (id, site_version_id, service_id)
            VALUES  (1, 1, 1),
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
                    (21, 1, 21),
                    (22, 1, 22),
                    (23, 1, 23),
                    (24, 1, 24),
                    (25, 1, 25),
                    (26, 1, 26),
                    (27, 1, 27),
                    (28, 1, 28),
                    (29, 1, 29),
                    (30, 1, 30),
                    (31, 1, 31),
                    (32, 1, 32),
                    (33, 1, 33),
                    (34, 1, 34),
                    (35, 1, 35),
                    (36, 1, 36),
                    (37, 1, 37),
                    (38, 1, 38),
                    (39, 1, 39),
                    (40, 2, 23);

        END IF;
    END;
$$;

-- Reset sequence
SELECT setval('services_by_site_versions_id_seq', (SELECT MAX(id) FROM services_by_site_versions));