DO
$$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM phone_numbers) THEN
            INSERT INTO phone_numbers (id, site_versions_id, phone_number)
            VALUES  (1, 1, '+84879347200'),
                    (2, 1, '+842363774555');
        END IF;
    END;
$$;

-- Reset sequence
SELECT setval('phone_numbers_id_seq', (SELECT MAX(id) FROM phone_numbers));