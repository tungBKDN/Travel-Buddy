DO
$$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM site_approvals) THEN
            INSERT INTO site_approvals (id, site_version_id, status, admin_id, approved_at)
            VALUES  (1, 1, 'APPROVED', 3, '2024-10-29 15:50:26.0001'),
                    (2, 2, 'APPROVED', 3, '2024-10-29 15:55:26.0001'),
                    (3, 3, 'APPROVED', 3, '2024-10-29 16:00:26.0001');
        END IF;
    END;
$$;

-- Reset sequence
SELECT setval('site_approvals_id_seq', (SELECT MAX(id) FROM site_approvals));