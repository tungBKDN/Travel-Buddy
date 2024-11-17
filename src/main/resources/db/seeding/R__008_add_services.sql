DO
$$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM services) THEN
            INSERT INTO services (id, service_name)
            VALUES (1, 'Bãi đỗ xe miễn phí'),
                   (2, 'Wi-Fi tốc độ - miễn phí'),
                   (3, 'Trung tâm thể dục thẩm mỹ'),
                   (4, 'Bể bơi'),
                   (5, 'Quầy bar / Sảnh chờ'),
                   (6, 'Bãi biển'),
                   (7, 'Sân chơi cho trẻ em'),
                   (8, 'Massage'),
                   (9, 'ATM trong khuôn viên'),
                   (10, 'Giặt là'),
                   (11, 'Tiếp tân 24 giờ'),
                   (12, 'Có phục vụ bữa sáng'),
                   (13, 'Đồ uống chào mừng miễn phí'),
                   (14, 'Cà phê pha chế miễn phí'),
                   (15, 'Cà phê hoòa tan miễn phí'),
                   (16, 'Đường trượt nước'),
                   (17, 'Nhà hàng trong nhà'),
                   (18, 'Bể bơi vô cực'),
                   (19, 'Bể bơi ngoài trời'),
                   (20, 'Xông hơi'),

                    (21, 'Màn che ánh sáng'),
                    (22, 'Phòng cách âm'),
                    (23, 'Điều hòa nhiệt độ'),
                    (24, 'Máy pha cà phê / trà'),
                    (25, 'Tủ lạnh'),
                    (26, 'Dọn phòng'),
                    (27, 'Ban công riêng'),
                    (28, 'Phòng tắm riêng'),
                    (29, 'Nước đóng chai miễn phí'),
                    (30, 'Tivi màn hình phẳng'),
                    (31, 'Máy sấy tóc'),
                    (32, 'Bồn tắm / vòi sen'),
                    (33, 'Máy sấy tóc'),
                    (34, 'Bàn làm việc'),
                    (35, 'Vòi xịt vệ sinh'),

                    (36, 'Phòng ngắm cảnh biển'),
                    (37, 'Phòng không hút thuốc'),
                    (38, 'Phòng Suite'),
                    (39, 'Phòng cho gia đình');
        END IF;
    END;
$$;

-- Reset sequence
SELECT setval('services_id_seq', (SELECT MAX(id) FROM services));