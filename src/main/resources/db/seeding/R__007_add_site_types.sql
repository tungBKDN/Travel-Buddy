DO
$$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM site_types) THEN
            INSERT INTO site_types (id, type_name, dual_state)
            VALUES  (1, 'Sân vận động', 'AMENITY'),
                    (2, 'Bệnh viện', 'AMENITY'),
                    (3, 'Khu vui chơi nước trong nhà', 'DUAL'),
                    (4, 'Hiệu thuốc', 'AMENITY');
        END IF;
    END;
$$;