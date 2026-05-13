# Tên chức năng: Danh sách độc giả

## Mục đích
Tra cứu độc giả và theo dõi tình trạng mượn sách hiện tại.

## Trigger
Người dùng mở menu **Độc giả**.

## Tiền điều kiện
Người dùng đã đăng nhập.

## Hậu điều kiện
Không thay đổi dữ liệu.

## Sơ đồ thực hiện
Mở danh sách -> Nhập điều kiện tra cứu -> Tìm kiếm -> Xem kết quả -> Chọn thao tác.

## Quy tắc nghiệp vụ
1. Hỗ trợ tìm theo mã độc giả, họ tên hoặc email.
2. Hỗ trợ lọc theo trạng thái độc giả.
3. Hiển thị số phiếu đang mượn để theo dõi rủi ro quá hạn.

## Bước thực hiện
| Bước | Chi tiết |
|---|---|
| 1 | Người dùng mở màn hình danh sách độc giả |
| 2 | Hệ thống hiển thị điều kiện lọc mặc định |
| 3 | Người dùng nhập điều kiện và chọn Tìm kiếm |
| 4 | Hệ thống hiển thị danh sách theo điều kiện |
| 5 | Người dùng chọn Chỉnh sửa/Ngừng sử dụng theo từng dòng |

## Giao diện chức năng
Bộ lọc tra cứu và bảng dữ liệu độc giả.

## Mô tả chi tiết giao diện
| STT | Field name | Loại control | Mô tả |
|---|---|---|---|
| 1 | Từ khóa | Textbox | Tìm theo mã, họ tên hoặc email |
| 2 | Trạng thái | Dropdown chọn một | Đang hoạt động/Ngừng hoạt động |
| 3 | Tìm kiếm | Button | Lọc danh sách |
| 4 | Làm mới | Button | Xóa điều kiện lọc |
| 5 | Bảng danh sách | Grid | Hiển thị: Mã độc giả, Họ tên, Email, SĐT, Số phiếu đang mượn, Trạng thái |
| 6 | Chỉnh sửa | Action tại dòng | Mở màn hình cập nhật độc giả |
| 7 | Ngừng sử dụng | Action tại dòng | Mở xác nhận ngừng sử dụng |
