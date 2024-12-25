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
                   (39, 'Phòng cho gia đình'),

                   (40, 'Tùy chọn không có glutein'),
                   (41, 'Chấp nhận thẻ tin dụng'),
                   (42, 'Bữa trưa'),
                   (43, 'Bữa tối'),
                   (44, 'Bữa sáng'),
                   (45, 'Đồ uống'),
                   (46, 'Ăn tối phòng riêng'),
                   (47, 'Đặt bàn trước'),
                   (48, 'Menu cho trẻ em'),
                   (49, 'Menu chay'),
                   (50, 'Menu thực đơn đặc biệt'),
                   (51, 'Menu thực đơn đặc biệt (theo yêu cầu)'),

                   (52, 'Thanh toán tiền mặt'),
                   (53, 'Thanh toán qua thẻ'),
                   (54, 'Thanh toán qua ví điện tử'),
                   (55, 'Thanh toán qua chuyển khoản'),

                   (56, 'Phòng tắm tại chỗ'),
                   (57, 'Phòng thay đồ chung theo giới tính'),
                   (58, 'Nhạc nền'),
                   (59, 'Huấn luyện viên theo buổi'),
                   (60, 'Phòng tắm hơi'),
                   (61, 'Massage giãn cơ'),

                   (62, 'Nơi đỗ xe hơi'),
                   (63, 'Đỗ xe miễn phí'),
                   (64, 'Giữ xe đạp'),
                   (65, 'Bãi giữ xe ngoài trời'),
                   (66, 'Hầm giữ xe'),
                   (67, 'Giữ xe có bảo vệ tài sản');

        END IF;
    END;
$$;

-- Reset sequence
SELECT setval('services_id_seq', (SELECT MAX(id) FROM services));