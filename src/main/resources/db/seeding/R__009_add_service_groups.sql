DO
$$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM service_groups) THEN
            INSERT INTO service_groups (id, service_group_name)
            VALUES (1, 'Tiện nghi'),
                   (2, 'Tiện nghi trong phòng'),
                   (3, 'Loại phòng');
        END IF;
    END;
$$;