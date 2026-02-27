# 🌟 SGU Heart - Nền Tảng Gây Quỹ Tình Nguyện

**SGU Heart** là nền tảng web gây quỹ từ thiện toàn diện, phát triển bằng **Spring Boot 3**. Dự án kết nối các nhà hảo tâm với những chương trình thiện nguyện ý nghĩa, mang đến trải nghiệm quyên góp an toàn, minh bạch và dễ quản trị.

---

## 🎯 Các Tính Năng Nổi Bật

### 🧑‍💻 Dành Cho Người Dùng (Client)
- **Đăng ký & Đăng nhập bảo mật**: Hỗ trợ đăng nhập truyền thống (BCrypt) và đăng nhập nhanh qua mạng xã hội (**Google**, **Facebook** OAuth2).
- **Duyệt & Tìm kiếm Chương trình**: Khám phá chiến dịch theo danh mục, theo dõi mục tiêu và số tiền thực tế.
- **Thanh toán Trực tuyến (VNPAY)**: Tích hợp VNPAY an toàn, hỗ trợ quét QR, thẻ ATM nội địa và thẻ quốc tế.
- **Tiến độ Thời gian Thực**: Thanh tiến độ (progress bar) tự động cập nhật sau khi giao dịch thành công.
- **Quản lý Hồ sơ Cá nhân**: Cập nhật thông tin, đổi ảnh đại diện, theo dõi lịch sử quyên góp (tải biên lai PDF).
- **Tin tức & Blog**: Cập nhật hoạt động tình nguyện với nội dung phong phú.

### 👑 Dành Cho Ban Quản Trị (Admin)
- **Dashboard Phân Tích Tổng Quan**:
  - Thống kê tự động: tổng số tiền quyên góp, số lượt quyên góp, số chiến dịch, số người dùng.
  - Biểu đồ trực quan (Chart.js): tỷ trọng theo chương trình/danh mục/hình thức thanh toán, Top 5 nhà tài trợ, tiến độ giải ngân chiến dịch, biểu đồ tăng trưởng người dùng mới.
- **Quản trị Toàn diện**:
  - Quản lý người dùng và phân quyền (Role-based access control).
  - Quản lý chiến dịch gây quỹ (Tạo mới, Sửa, Đóng chiến dịch).
  - Duyệt danh sách khoản đóng góp và giao dịch tài chính.
  - Đăng bài viết tin tức (Blog).
- **Giao diện Quản trị Hiện đại**: Dark Theme sang trọng, dễ nhìn, hiệu năng máy chủ ổn định và mượt mà.

---

## 📸 Hình Ảnh Dự Án (Screenshots)

### 1. Giao diện Trang chủ (Client Homepage)
Giao diện sáng sủa với các nút kêu gọi hành động (Call To Action) rõ ràng, làm nổi bật những chương trình tiêu biểu.

![SGU Heart Homepage](docs/images/homepage.png)

### 2. Chi tiết Chương trình & Tin Tức
Hiển thị đầy đủ thông tin chương trình, thanh tiến độ quyên góp và các chiến dịch liên quan. Nội dung Blog được trình bày rõ ràng, giàu thông tin.

![Blog Update Detail](docs/images/blog_detail.png)

### 3. Tích hợp Thanh toán VNPAY (Quyên góp)
Thanh toán nhanh chóng, an toàn qua VNPAY Sandbox ngay trên nền tảng với đầy đủ thông tin giao dịch.

![VNPAY Secure Checkout](docs/images/vnpay_checkout_v2.webp)

### 4. Bảng điều khiển Quản trị (Admin Dashboard)
Tổng hợp dữ liệu hệ thống bằng số liệu và các biểu đồ chuyên nghiệp (Pie, Doughnut, Bar, Line).

![Admin Dashboard Analytics](docs/images/admin_dashboard_v4.webp)

### 5. Quản lý Đa năng dành cho Admin (Management Interfaces)
Quản trị viên theo dõi danh sách người dùng, thay đổi trạng thái chiến dịch và quản lý quyên góp trên giao diện lưới (Data Table) với tìm kiếm (Search) và sắp xếp (Sort) linh hoạt.

![Admin Management Interfaces](docs/images/admin_management.webp)

---

## ⚙️ Công Nghệ Sử Dụng (Tech Stack)

### Backend
- **Java 21**
- **Spring Boot 3** (Spring Web, Spring Data JPA, Spring Security)
- **Hibernate / JPA**
- **Thymeleaf** (Template Engine render HTML trực tiếp từ server)
- **OAuth2 Client** (Xác thực Google/Facebook)
- **Cổng thanh toán:** API VNPAY
- **Cơ sở dữ liệu:** MySQL 8

### Frontend
- **HTML5, CSS3, JavaScript (Vanilla)**
- **Bootstrap 5** (Responsive UI/UX)
- **Chart.js** (Khởi tạo biểu đồ cho Admin Dashboard)

### Triển Khai (Deployment) & Công Cụ
- **Docker & Docker Compose**: Ứng dụng và cơ sở dữ liệu (MySQL) được đóng gói sẵn trong Container, chỉ cần chạy qua lệnh `docker compose up`.
- **Git / GitHub**

---

## 🚀 Hướng Dẫn Cài Đặt (Local Setup)

Dự án đã được đóng gói sẵn với Docker, việc cài đặt và chạy local rất nhanh chóng.

### Điều kiện tiên quyết
- Đã cài đặt [Docker Desktop](https://www.docker.com/products/docker-desktop).

### Các bước chạy
1.  **Clone mã nguồn dự án:**
    ```bash
    git clone https://github.com/thanhtrongvo/SpringDonateWeb.git
    cd SpringDonateWeb
    ```

2.  **Khởi chạy với Docker Compose:**
    ```bash
    docker compose up -d --build
    ```
    Hệ thống sẽ tự động tải MySQL 8.0, build file `.jar` của Spring Boot (Java 21) và liên kết 2 container với nhau.

3.  **Truy cập ứng dụng:**
    - Cổng người dùng (Client): `http://localhost:8080/`
    - Cổng quản trị (Admin): `http://localhost:8080/admin/index`
        - *Tài khoản Admin mặc định:* `admin@sguheart.com`
        - *Mật khẩu:* `password123`


---
