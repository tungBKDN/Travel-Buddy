DO
$$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM sites) THEN
            INSERT INTO sites (id, owner_id)
            VALUES  (1, 1),
             (2, 1),
             (3, 2);
        END IF;
    END;
$$;

-- Reset sequence
SELECT setval('sites_id_seq', (SELECT MAX(id) FROM sites));