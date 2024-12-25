DO
$$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM site_types) THEN
            INSERT INTO site_types (type_name, dual_state)
            VALUES ('Sân vận động', 'AMENITY'),       -- 1
                   ('Bệnh viện', 'AMENITY'),
                   ('Khu vui chơi nước trong nhà', 'DUAL'),
                   ('Hiệu thuốc', 'AMENITY'),
                   ('Nhà hàng', 'DUAL'),              -- 5
                   ('Nhà hàng thức ăn nhanh', 'AMENITY'),
                   ('Nhà hàng thuần chay', 'AMENITY'),
                   ('Nhà hàng thịt nướng', 'AMENITY'),
                   ('Nhà hàng Brunch', 'AMENITY'),
                   ('Quán ăn nhỏ', 'DUAL'),           -- 10
                   ('Nhà hàng thực phẩm lành mạnh', 'AMENITY'),
                   ('Nhà hàng chay', 'AMENITY'),
                   ('Nhà hàng cao cấp', 'DUAL'),
                   ('Nhà hàng hải sản', 'AMENITY'),
                   ('Nhà điều hành tour', 'AMENITY'), -- 15
                   ('Nhà hàng gia đình', 'DUAL'),
                   ('Nhà hàng bít tết', 'AMENITY'),
                   ('Quán tapas', 'AMENITY'),
                   ('Quán mì', 'AMENITY'),
                   ('Nhà hàng cơm', 'DUAL'),          -- 20
                   ('Quán cà phê', 'AMENITY'),
                   ('Quầy xúc xích nóng', 'AMENITY'),
                   ('Quán bánh mì', 'DUAL'),
                   ('Nhà hàng buffet', 'DUAL'),
                   ('Quầy đồ ăn nhẹ', 'AMENITY'),
                   ('Điểm du lịch', 'ATTRACTION'),
                   ('Khách sạn', 'AMENITY'),
                   ('Khu nghỉ dưỡng khách sạn (Resort hotel)', 'ATTRACTION'),
                   ('Quán bia thủ công', 'DUAL'),
                   ('Hoa viên', 'ATTRACTION'),
                   ('Nhà vườn cho thuê', 'DUAL'),
                   ('Cửa hàng thực phẩm', 'AMENITY'),
                   ('Nhà nghỉ dưỡng', 'DUAL'),
                   ('Homestay', 'DUAL'),
                   ('Pub', 'AMENITY'),
                   ('Căn hộ nghỉ dưỡng', 'DUAL'),
                   ('Khu cắm trại', 'ATTRACTION'),
                   ('Phòng GYM', 'AMENITY'),
                   ('Nhà thờ', 'DUAL'),
                   ('Villa', 'DUAL'),
                   ('Nông trại', 'ATTRACTION'),
                   ('Vườn', 'ATTRACTION'),
                   ('Vườn cây', 'ATTRACTION'),
                   ('Công viên', 'ATTRACTION'),
                   ('Nông trại cắm trại', 'ATTRACTION'),
                   ('Khu đi bộ', 'AMENITY'),
                   ('Cơ quan du lịch lặn', 'AMENITY'),
                   ('Công viên sinh thái', 'ATTRACTION'),
                   ('Công viên tưởng niệm', 'ATTRACTION'),
                   ('Rừng quốc gia', 'ATTRACTION'),
                   ('Công viên đồng quê', 'ATTRACTION'),
                   ('Vườn bách thảo', 'ATTRACTION'),
                   ('Quán ăn', 'DUAL'); -- 53

        END IF;
    END;
$$;

-- Reset sequence
SELECT setval('site_types_id_seq', (SELECT MAX(id) FROM site_types));