/**
 * Admin Panel JavaScript
 * Tất cả các chức năng JavaScript cho phần Admin sẽ được đặt trong đây
 */

document.addEventListener('DOMContentLoaded', function () {
    // Sidebar toggle
    const sidebarToggle = document.querySelector('#sidebarToggle');
    const sidebar = document.querySelector('#sidebar');
    const content = document.querySelector('#content');

    if (sidebarToggle) {
        sidebarToggle.addEventListener('click', function () {
            sidebar.classList.toggle('collapsed');
            if (window.innerWidth > 768) {
                content.classList.toggle('expanded');
            }
        });
    }

    // Responsive check - đóng sidebar khi màn hình nhỏ
    function checkWidth() {
        if (window.innerWidth < 768) {
            sidebar?.classList.add('collapsed');
        } else {
            sidebar?.classList.remove('collapsed');
        }
    }

    // Kiểm tra kích thước khi tải trang
    window.addEventListener('load', checkWidth);
    window.addEventListener('resize', checkWidth);

    // Khởi tạo DataTables nếu tồn tại (với namespace .admin-panel)
    const dataTablesElements = document.querySelectorAll('.datatable');
    if (dataTablesElements.length > 0) {
        dataTablesElements.forEach(table => {
            $(table).DataTable({
                responsive: true,
                language: {
                    search: "Tìm kiếm:",
                    lengthMenu: "Hiển thị _MENU_ mục",
                    info: "Hiển thị _START_ đến _END_ của _TOTAL_ mục",
                    infoEmpty: "Hiển thị 0 đến 0 của 0 mục",
                    infoFiltered: "(được lọc từ _MAX_ mục)",
                    zeroRecords: "Không tìm thấy dữ liệu phù hợp",
                    paginate: {
                        first: "Đầu",
                        last: "Cuối",
                        next: "Tiếp",
                        previous: "Trước"
                    }
                }
            });
        });
    }

    // Khởi tạo tooltip
    const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]');
    if (tooltipTriggerList.length > 0) {
        const tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl));
    }

    // Xử lý form filters với namespace .admin-panel
    const filterToggles = document.querySelectorAll('#filterToggle');
    filterToggles.forEach(toggle => {
        toggle.addEventListener('click', function () {
            const filterPanel = this.closest('.card').querySelector('#filterPanel');
            if (filterPanel) {
                if (filterPanel.style.display === 'none') {
                    filterPanel.style.display = 'block';
                } else {
                    filterPanel.style.display = 'none';
                }
            }
        });
    });

    // Xử lý các sự kiện modal trong admin panel
    const adminModals = document.querySelectorAll('.modal');
    if (adminModals.length > 0) {
        adminModals.forEach(modal => {
            modal.addEventListener('show.bs.modal', function (event) {
                // Có thể thêm code xử lý khi modal mở nếu cần
            });
        });
    }

    // Xử lý sự kiện cho nút xóa với confirm dialog
    const deleteButtons = document.querySelectorAll('.btn-delete');
    if (deleteButtons.length > 0) {
        deleteButtons.forEach(button => {
            button.addEventListener('click', function (e) {
                if (!confirm('Bạn có chắc chắn muốn xóa mục này không?')) {
                    e.preventDefault();
                }
            });
        });
    }
});

// Các hàm tiện ích dùng trong quản trị

/**
 * Format số thành định dạng tiền tệ
 * @param {number} amount - Số tiền cần định dạng
 * @param {string} currency - Ký hiệu tiền tệ (mặc định: VND)
 * @returns {string} Chuỗi đã định dạng
 */
function formatCurrency(amount, currency = 'VND') {
    return new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: currency
    }).format(amount);
}

/**
 * Format ngày tháng theo định dạng Việt Nam
 * @param {string|Date} date - Ngày cần định dạng
 * @returns {string} Chuỗi ngày đã định dạng
 */
function formatDate(date) {
    if (!date) return '';
    const d = new Date(date);
    return d.toLocaleDateString('vi-VN');
}

/**
 * Hiển thị thông báo toast
 * @param {string} message - Nội dung thông báo
 * @param {string} type - Loại thông báo (success, error, warning, info)
 */
function showToast(message, type = 'info') {
    // Kiểm tra nếu đã có container toast
    let toastContainer = document.querySelector('.toast-container');

    // Nếu chưa có, tạo mới
    if (!toastContainer) {
        toastContainer = document.createElement('div');
        toastContainer.className = 'toast-container position-fixed bottom-0 end-0 p-3';
        document.body.appendChild(toastContainer);
    }

    // Tạo ID duy nhất cho toast
    const toastId = 'toast-' + Date.now();

    // Map loại toast sang Bootstrap class
    const typeClass = {
        'success': 'bg-success',
        'error': 'bg-danger',
        'warning': 'bg-warning',
        'info': 'bg-info'
    };

    // Tạo HTML cho toast
    const toastHtml = `
        <div id="${toastId}" class="toast align-items-center ${typeClass[type] || 'bg-info'} text-white border-0" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="d-flex">
                <div class="toast-body">
                    ${message}
                </div>
                <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
        </div>
    `;

    // Thêm toast vào container
    toastContainer.insertAdjacentHTML('beforeend', toastHtml);

    // Khởi tạo toast và hiển thị
    const toastElement = document.getElementById(toastId);
    const toast = new bootstrap.Toast(toastElement, {
        autohide: true,
        delay: 5000
    });
    toast.show();
}