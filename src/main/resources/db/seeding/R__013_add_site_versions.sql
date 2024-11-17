DO
$$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM site_versions) THEN
            INSERT INTO site_versions (id, site_id, site_name, lat, lng, resolved_address, website, created_at, type_id)
            VALUES (1, 1, 'Công viên nước Đà Nẵng Mikazuki', 16.092438, 108.143831, 'Khu du lịch Xuân Thiều, đường Nguyễn Tất Thành, phường Hòa Hiệp Nam, quận Liên Chiểu, TP. Đà Nẵng', 'https://www.mikazukiwaterpark.com/', '2024-10-28 10:18:20', 3),
            (2, 2, 'Quầy thuốc Thiện Toàn', 16.07289, 108.15017, '44 Nguyễn Lương Bằng, phường Hòa Khánh Bắc, quận Liên Chiểu, TP. Đà Nẵng', null, '2024-10-29 00:18:23.717194', 4),
            (3, 3, 'Sân vận động quận Liên Chiểu', 16.081110, 108.152360, '2 Trần Đình Tri, phường Hòa Minh, quận Liên Chiểu, TP. Đà Nẵng', null, '2024-10-29 00:22:23.711111', 1);
            END IF;
    END;
$$;

-- Reset sequence
SELECT setval('site_versions_id_seq', (SELECT MAX(id) FROM site_versions));