DO
$$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM service_groups) THEN
            INSERT INTO service_groups (id, service_group_name)
            VALUES (1, 'Tiện nghi'),
                   (2, 'Tiện nghi trong phòng'),
                   (3, 'Loại phòng'),
                   (4, 'Dịch vụ ăn uống'),
                   (5, 'Thanh toán'),
                   (6, 'Vệ sinh thể dục'),
                   (7, 'Xe cộ');
        END IF;
    END;
$$;

-- Reset sequence
SELECT setval('service_groups_id_seq', (SELECT MAX(id) FROM service_groups));