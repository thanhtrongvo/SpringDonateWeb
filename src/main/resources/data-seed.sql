-- ============================================
-- SpringDonateWeb - Seed Data
-- ============================================

USE webmomo;

SET FOREIGN_KEY_CHECKS=0;
TRUNCATE TABLE transactions;
TRUNCATE TABLE donations;
TRUNCATE TABLE blogs;
TRUNCATE TABLE programs;
TRUNCATE TABLE categories;
TRUNCATE TABLE paymentmethods;
TRUNCATE TABLE fundcommon;
TRUNCATE TABLE users;
SET FOREIGN_KEY_CHECKS=1;

-- ============================================
-- 1. Users (password: 123456 - BCrypt encoded)
-- ============================================
-- roleId: 1 = ADMIN, 2 = USER
INSERT INTO users (name, email, password, role_id, address, phone_number, status) VALUES
('Admin SGU', 'admin@sguheart.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 1, '273 An Dương Vương, Quận 5, TP.HCM', '0901234567', true),
('Nguyễn Văn An', 'an.nguyen@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 2, '123 Nguyễn Trãi, Quận 1, TP.HCM', '0912345678', true),
('Trần Thị Bình', 'binh.tran@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 2, '456 Lê Lợi, Quận 3, TP.HCM', '0923456789', true),
('Lê Minh Châu', 'chau.le@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 2, '789 Võ Văn Tần, Quận 10, TP.HCM', '0934567890', true),
('Phạm Đức Duy', 'duy.pham@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 2, '321 Hai Bà Trưng, Quận 1, TP.HCM', '0945678901', true);

-- ============================================
-- 2. Categories
-- ============================================
INSERT INTO categories (name, description) VALUES
('Học bổng', 'Hỗ trợ học bổng cho sinh viên có hoàn cảnh khó khăn'),
('Y tế', 'Hỗ trợ chi phí y tế, khám chữa bệnh'),
('Thiên tai', 'Cứu trợ thiên tai, bão lũ'),
('Giáo dục', 'Hỗ trợ cơ sở vật chất, trang thiết bị học tập');

-- ============================================
-- 3. Programs
-- ============================================
INSERT INTO programs (name, description, goal_amount, current_amount, image, donation_count, start_date, end_date, status, category_id) VALUES
('Học bổng Ước Mơ Xanh', 'Chương trình hỗ trợ học bổng cho 50 sinh viên có hoàn cảnh khó khăn tại các trường đại học trên địa bàn TP.HCM', 200000000, 67500000, '/img/program/program_1.jpg', 156, '2026-01-01', '2026-06-30', true, 1),
('Chắp Cánh Tri Thức', 'Quyên góp sách vở, dụng cụ học tập cho các em học sinh vùng cao Tây Nguyên', 150000000, 98000000, '/img/program/program_2.jpg', 234, '2026-01-15', '2026-05-15', true, 4),
('Trái Tim Nhân Ái', 'Hỗ trợ chi phí phẫu thuật tim cho trẻ em nghèo mắc bệnh tim bẩm sinh', 500000000, 325000000, '/img/program/program_3.jpg', 512, '2025-11-01', '2026-04-30', true, 2),
('Mái Ấm Tình Thương', 'Xây dựng nhà tình thương cho các hộ gia đình bị ảnh hưởng bởi bão lũ tại miền Trung', 300000000, 189000000, '/img/program/program_4.jpg', 378, '2026-02-01', '2026-08-31', true, 3),
('Ánh Sáng Học Đường', 'Lắp đặt hệ thống điện mặt trời cho 10 trường tiểu học vùng sâu vùng xa', 250000000, 45000000, '/img/program/program_1.jpg', 89, '2026-02-15', '2026-12-31', true, 4),
('Bữa Cơm Yêu Thương', 'Cung cấp bữa ăn miễn phí cho 200 sinh viên nghèo mỗi ngày trong 6 tháng', 180000000, 120000000, '/img/program/program_2.jpg', 445, '2026-01-01', '2026-07-01', true, 1),
('Cùng Em Đến Trường', 'Hỗ trợ xe đạp và học phí cho 100 em học sinh nghèo ở Đồng bằng Sông Cửu Long', 100000000, 78000000, '/img/program/program_3.jpg', 267, '2026-03-01', '2026-09-30', true, 4),
('Nụ Cười Khỏe Mạnh', 'Khám và điều trị nha khoa miễn phí cho 500 trẻ em vùng nông thôn', 120000000, 55000000, '/img/program/program_4.jpg', 134, '2026-02-01', '2026-07-31', true, 2);

-- ============================================
-- 4. Fund Common (Quỹ chung)
-- ============================================
INSERT INTO fundcommon (name, note, current_amount) VALUES
('Quỹ Chung SGU Heart', 'Quỹ chung dùng để hỗ trợ các hoạt động từ thiện của SGU Heart', 15000000);

-- ============================================
-- 5. Payment Methods
-- ============================================
INSERT INTO paymentmethods (method_name, description, is_active) VALUES
('VNPay', 'Thanh toán qua cổng VNPay', 1),
('Chuyển khoản', 'Chuyển khoản ngân hàng trực tiếp', 1),
('Tiền mặt', 'Đóng góp bằng tiền mặt', 1);

-- ============================================
-- 6. Donations
-- ============================================
INSERT INTO donations (user_id, program_id, amount, donation_date, donor_name) VALUES
(2, 1, 500000.00, '2026-01-15 10:30:00', 'Nguyễn Văn An'),
(3, 1, 1000000.00, '2026-01-20 14:15:00', 'Trần Thị Bình'),
(4, 2, 2000000.00, '2026-02-01 09:00:00', 'Lê Minh Châu'),
(5, 3, 5000000.00, '2026-02-05 16:45:00', 'Phạm Đức Duy'),
(2, 3, 1500000.00, '2026-02-10 11:20:00', 'Nguyễn Văn An'),
(3, 4, 3000000.00, '2026-02-12 08:30:00', 'Trần Thị Bình'),
(4, 1, 750000.00, '2026-02-15 13:00:00', 'Lê Minh Châu'),
(5, 2, 1000000.00, '2026-02-18 15:30:00', 'Phạm Đức Duy'),
(2, 5, 200000.00, '2026-02-20 10:00:00', 'Nguyễn Văn An'),
(3, 6, 500000.00, '2026-02-21 12:00:00', 'Trần Thị Bình'),
(4, 7, 1000000.00, '2026-02-22 14:30:00', 'Lê Minh Châu'),
(5, 8, 300000.00, '2026-02-23 09:15:00', 'Phạm Đức Duy'),
(2, 4, 2500000.00, '2026-02-23 17:00:00', 'Nguyễn Văn An'),
(3, 5, 1500000.00, '2026-02-24 08:00:00', 'Trần Thị Bình'),
(4, 6, 800000.00, '2026-02-24 10:30:00', 'Lê Minh Châu');

-- ============================================
-- 7. Blogs
-- ============================================
INSERT INTO blogs (title, content, image_url, created_at, updated_at, status) VALUES
('SGU Heart chính thức khởi động mùa từ thiện 2026', '<div class="blog-content"><p class="lead">Năm 2026, <strong>SGU Heart</strong> tiếp tục sứ mệnh lan tỏa yêu thương đến cộng đồng. Với 8 chương trình từ thiện mới, chúng tôi hướng đến mục tiêu giúp đỡ hơn 1000 sinh viên và trẻ em có hoàn cảnh khó khăn trên khắp Việt Nam.</p><h5>Những điểm nhấn trong năm 2026:</h5><ul><li><strong>Mở rộng quy mô:</strong> Tổ chức các đoàn thiện nguyện đến tận các vùng sâu vùng xa ở khu vực Tây Nguyên và miền núi phía Bắc.</li><li><strong>Đa dạng hóa chương trình:</strong> Bổ sung quỹ hỗ trợ sinh viên nghèo vượt khó, y tế học đường, và chăm sóc sức khỏe cộng đồng.</li><li><strong>Tăng cường minh bạch:</strong> Tất cả các khoản tiền quỹ thu được sẽ đưọc thống kê công khai và tự động cập nhật liên tục qua hệ thống Website SGU Heart vừa ra mắt.</li></ul><p>Mọi đóng góp, dù nhỏ bé, đều mang lại giá trị to lớn cho những người cần giúp đỡ. Sự đồng hành của các nhà hảo tâm chính là động lực lớn nhất để chúng tôi tiếp bước. <em>Hãy cùng chúng tôi viết tiếp câu chuyện nhân ái!</em></p></div>', 'blog_1.jpg', '2026-01-05 08:00:00', '2026-01-05 08:00:00', true),
('Trao học bổng Ước Mơ Xanh đợt 1 cho 20 sinh viên', '<div class="blog-content"><p>Ngày 20/01/2026, câu lạc bộ <strong>SGU Heart</strong> đã long trọng tổ chức lễ trao học bổng đợt 1 của chương trình <em>Ước Mơ Xanh</em> dành cho sinh viên trường Đại Học Sài Gòn (SGU).</p><p>Tại buổi lễ, 20 bạn sinh viên xuất sắc vượt khó vươn lên trong học tập đã được vinh danh và nhận tận tay những phần học bổng giá trị.</p><div class="row my-4"><div class="col-md-6"><img src="/img/blog/blog_2.jpg" class="img-fluid rounded shadow-sm" alt="Trao học bổng"></div><div class="col-md-6"><p><strong>Mỗi suất học bổng trị giá 5.000.000 VNĐ</strong>. Tuy số tiền không quá lớn, nhưng đây là nguồn động viên tinh thần vô giá giúp các bạn trang trải chi phí học tập và sinh hoạt phí trong học kỳ mới, giảm bớt gánh nặng trên vai gia đình.</p></div></div><p>Đại diện Ban chủ nhiệm câu lạc bộ phát biểu: "<em>Chúng tôi tin rằng, giáo dục chính là chiếc chìa khóa vạn năng để thay đổi số phận. Quỹ Ước Mơ Xanh cam kết sẽ luôn đồng hành cùng các bạn trên con đường chinh phục tri thức.</em>"</p></div>', 'blog_2.jpg', '2026-01-21 10:00:00', '2026-01-21 10:00:00', true),
('Hành trình thiện nguyện vùng cao Tây Nguyên', '<div class="blog-content"><p>Đầu tháng 2, Đoàn tình nguyện SGU Heart đã có chuyến xe yêu thương kéo dài 3 ngày 2 đêm đến các trường học vùng cao tại tỉnh Kon Tum.</p><h5>Chuyến xe chuyên chở yêu thương</h5><p>Chuyến đi đã vận chuyển thành công và trao tặng trực tiếp:</p><ul><li>500 bộ sách giáo khoa từ lớp 1 đến lớp 5</li><li>300 ba lô mới chống gù lưng</li><li>Hơn 1.000 bộ dụng cụ học tập (vở, bút, thước con compa)</li><li>50 suất quà nhu yếu phẩm cho các hộ dân có hoàn cảnh đặc biệt khó khăn</li></ul><p>Ngoài các hoạt động trao quà, các tình nguyện viên của SGU Heart còn trực tiếp sơn sửa lại 2 phòng học cũ, tổ chức giao lưu văn nghệ, các trò chơi vận động và dạy hát cho hơn 200 em học sinh dân tộc thiểu số tại bản làng.</p><p>Nhìn những nụ cười tỏa nắng trên khuôn mặt rạng rỡ của các em khi nhận được sách vở mới, bao mệt nhọc của chặng đường đèo dốc như tan biến hết. Trái tim yêu thương đã thực sự chạm đến đúng những nơi cần nhất!</p></div>', 'blog_3.jpg', '2026-02-01 09:30:00', '2026-02-01 09:30:00', true),
('Phẫu thuật tim thành công cho 5 trẻ em nghèo', '<div class="blog-content"><p class="lead text-primary">Tin vui từ quỹ <strong>Trái Tim Nhân Ái</strong>: Cả 5 ca phẫu thuật tim bẩm sinh trong tháng vừa qua đều đã thành công tốt đẹp!</p><p>Chương trình <em>Trái Tim Nhân Ái</em> (SGU Heart) đã phối hợp chặt chẽ cùng các y bác sĩ tại Bệnh viện Nhi Đồng trong suốt 2 tuần qua để tiến hành can thiệp phẫu thuật cho 5 bệnh nhi có hoàn cảnh khó khăn mắc bệnh lý dị tật tim bẩm sinh.</p><p>Tổng chi phí phẫu thuật, viện phí và thuốc men hỗ trợ điều trị hậu phẫu lên tới <strong>hơn 250 triệu đồng</strong> - số tiền này đã được quý mạnh thường quân và các nhà hảo tâm quyên góp thông qua nền tảng SGU Heart trong suốt tháng 1.</p><h5>Tình hình sức khỏe các bé</h5><p>Hiện tại, cả 5 bé đều đã qua cơn nguy kịch, rút ống thở và đang phục hồi sức khỏe rất nhanh tại phòng Hồi sức tích cực. Dự kiến các bé sẽ được xuất viện và trở về vòng tay ấm áp của gia đình trong tuần tới.</p><p>Thay mặt các gia đình bệnh nhi, SGU Heart xin gửi lời tri ân sâu sắc nhất đến toàn thể những tấm lòng vàng đã chung tay giành lại sự sống cho các mầm non tương lai!</p></div>', 'blog_4.jpg', '2026-02-10 14:00:00', '2026-02-10 14:00:00', true),
('Khánh thành 3 nhà tình thương tại Quảng Bình', '<div class="blog-content"><p>Giữa tiết trời se lạnh của tháng 2, sự ấm áp của tình người đã tỏa sáng tại rốn lũ Lệ Thủy - tỉnh Quảng Bình.</p><p>SGU Heart cùng đại diện các nhà hảo tâm đã long trọng cắt băng khánh thành và bàn giao <strong>3 căn nhà tình thương</strong> cho 3 hộ gia đình bị thiệt hại nhà cửa nặng nề nhất sau đợt bão lũ khủng khiếp hồi cuối năm ngoái.</p><p>Mỗi căn nhà được thiết kế theo tiêu chuẩn chống bão lũ của địa phương:</p><ul><li>Diện tích mặt sàn 50m² vững chãi.</li><li>Nền móng được nâng cao 1.5m để tránh ngập lụt.</li><li>Trần đổ bê tông chịu lực tốt.</li></ul><p>Phát biểu tại buổi bàn giao, bà Nguyễn Thị M., một trong ba hộ dân được nhận nhà, xúc động rơi nước mắt: "<em>Từ nay mẹ con tui không còn nơm nớp lo sợ mỗi đêm mưa bão về nữa. Mái ấm này là gia tài lớn nhất mà chúng tui hằng mơ ước.</em>"</p></div>', 'blog_5.jpg', '2026-02-15 11:00:00', '2026-02-15 11:00:00', true),
('Ngày hội hiến máu nhân đạo SGU 2026', '<div class="blog-content"><p>Nằm trong chuỗi hoạt động <em>“Giọt Hồng Chia Sẻ”</em>, ngày hội hiến máu nhân đạo lần 1 năm 2026 do Câu lạc bộ SGU Heart phối hợp với Hội Chữ Thập Đỏ và Bệnh viện Truyền máu Huyết học TP.HCM đã diễn ra thành công rực rỡ.</p><p>Tại sân trường Đại Học Sài Gòn, sự kiện đã thu hút <strong>hơn 300 sinh viên, giảng viên và tình nguyện viên</strong> tham gia nhiệt tình từ sáng sớm. Kết quả cuối ngày, ban tổ chức đã thu nhận được <strong>250 đơn vị máu</strong> đạt tiêu chuẩn.</p><h5>Ý nghĩa chương trình</h5><p>Mỗi giọt máu cho đi, một cuộc đời ở lại. Số lượng máu quý giá này đã ngay lập tức được chuyển về ngân hàng máu thành phố, góp phần giải quyết tình trạng thiếu hụt máu dự trữ trầm trọng sau dịp Tết Nguyên Đán, đảm bảo nguồn máu cấp cứu cứu sống bệnh nhân.</p><p>Cảm ơn tất cả các bạn đã trao đi không chỉ những giọt máu hồng mà còn là tình yêu thương vô giá. Hẹn gặp lại các bạn ở đợt hiến máu lần 2 dự kiến vào tháng 8 tới!</p></div>', 'blog_6.jpg', '2026-02-18 08:30:00', '2026-02-18 08:30:00', true),
('Workshop Kỹ năng mềm cho sinh viên khó khăn', '<div class="blog-content"><p>SGU Heart không chỉ mang đến sự hỗ trợ về tài chính mà còn mong muốn trang bị hành trang tri thức vững chắc cho các bạn trẻ.</p><p>Tuần qua, chuỗi workshop miễn phí mang tên <strong>Nâng Bước Thành Công</strong> đã được tổ chức dành riêng cho hơn 150 sinh viên có hoàn cảnh đặc biệt khó khăn trên địa bàn thành phố.</p><h5>Chủ đề các buổi hướng dẫn:</h5><ol><li>Kỹ năng giao tiếp và thuyết trình ấn tượng trước đám đông.</li><li>Phương pháp quản lý thời gian và tài chính cá nhân hiệu quả cho sinh viên xa nhà.</li><li>Kỹ năng xây dựng CV và chinh phục nhà tuyển dụng khắc khe.</li><li>Làm việc nhóm và tư duy giải quyết vấn đề.</li></ol><p>Chương trình có sự góp mặt giảng dạy trực tiếp của các diễn giả là CEO, chuyên gia nhân sự cấp cao đến từ các Tập đoàn đối tác của quỹ SGU Heart. Cuối khóa, 100% sinh viên tham gia đánh giá rất hài lòng và tự tin hơn rất nhiều vào bộ kỹ năng của mình sau khi ra trường, sẵn sàng hội nhập với thị trường lao động cạnh tranh.</p></div>', 'blog_7.jpg', '2026-02-20 09:00:00', '2026-02-20 09:00:00', true),
('Bếp ăn yêu thương phục vụ 10.000 suất cơm', '<div class="blog-content"><p class="lead">Một cột mốc đáng tự hào đã được xác lập: Chương trình <strong>Bữa Cơm Yêu Thương</strong> chính thức vượt mốc 10.000 suất ăn miễn phí chỉ sau đúng 2 tháng đi vào hoạt động!</p><p>Ra đời với mục đích san sẻ gánh nặng vật giá ngày càng leo thang đối với sinh viên ngoại tỉnh, bếp ăn SGU Heart hoạt động liên tục các ngày trong tuần từ Thứ 2 đến Thứ 6. Mỗi suất cơm tuy miễn phí nhưng vẫn đảm bảo <strong>3 tiêu chí vàng</strong>:</p><ul><li>Đầy đủ dinh dưỡng (cơm, mặn, xào, canh).</li><li>An toàn vệ sinh thực phẩm 100%.</li><li>Thực đơn thay đổi đa dạng mỗi ngày.</li></ul><p>Tọa lạc ngay cạnh Ký túc xá Đại học Sài Gòn, mỗi trưa từ 11h - 13h, bếp ăn lại rộn rã tiếng cười nói của các bạn sinh viên. Những phần cơm nóng hổi, tiếp thêm năng lượng cho những buổi học chiều căng thẳng trên giảng đường.</p><p>SGU Heart xin chân thành cảm ơn sự tài trợ nguyên liệu lương thực định kỳ từ các đại lý, siêu thị trên địa bàn, cùng công sức không quản mưa nắng của hơn 20 tình nguyện viên cốt cán đã gắn bó với Bếp từ những ngày đầu tiên.</p></div>', 'blog_8.jpg', '2026-02-22 12:00:00', '2026-02-22 12:00:00', true);

-- ============================================
-- 8. Transactions
-- ============================================
INSERT INTO transactions (donation_id, payment_method_id, amount, transaction_date, status) VALUES
(1, 1, 500000.00, '2026-01-15 10:31:00', 'SUCCESS'),
(2, 1, 1000000.00, '2026-01-20 14:16:00', 'SUCCESS'),
(3, 1, 2000000.00, '2026-02-01 09:01:00', 'SUCCESS'),
(4, 1, 5000000.00, '2026-02-05 16:46:00', 'SUCCESS'),
(5, 2, 1500000.00, '2026-02-10 11:21:00', 'SUCCESS'),
(6, 1, 3000000.00, '2026-02-12 08:31:00', 'SUCCESS'),
(7, 1, 750000.00, '2026-02-15 13:01:00', 'SUCCESS'),
(8, 2, 1000000.00, '2026-02-18 15:31:00', 'SUCCESS'),
(9, 1, 200000.00, '2026-02-20 10:01:00', 'SUCCESS'),
(10, 1, 500000.00, '2026-02-21 12:01:00', 'SUCCESS');
