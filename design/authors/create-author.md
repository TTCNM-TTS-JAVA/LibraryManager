# Tên chức năng: Tạo mới tác giả

## Mục đích
Thêm tác giả mới vào danh mục để gắn cho sách khi nhập hoặc cập nhật thông tin sách.

## Trigger
Người dùng truy cập màn hình **Danh mục tác giả** và chọn **Tạo mới**.

## Tiền điều kiện
1. Người dùng đã đăng nhập.
2. Người dùng có quyền quản lý danh mục.

## Hậu điều kiện
Tác giả mới được thêm và có thể chọn trong màn hình sách.

## Sơ đồ thực hiện
Mở danh mục tác giả -> Tạo mới -> Nhập thông tin -> Lưu -> Kiểm tra hợp lệ -> Hoàn tất.

## Quy tắc nghiệp vụ
1. Tên đầy đủ là bắt buộc.
2. Không cho tạo trùng tên tác giả đang dùng.
3. Bút danh và mô tả là thông tin bổ sung, không bắt buộc.

## Bước thực hiện
| Bước | Chi tiết |
|---|---|
| 1 | Người dùng chọn Tạo mới tác giả |
| 2 | Hệ thống mở biểu mẫu nhập |
| 3 | Người dùng nhập thông tin |
| 4 | Người dùng chọn Lưu |
| 5 | Hệ thống kiểm tra dữ liệu bắt buộc và trùng tên |
| 6 | Nếu lỗi, hiển thị thông báo tại từng trường |
| 7 | Nếu hợp lệ, hệ thống ghi nhận dữ liệu |
| 8 | Hiển thị thông báo tạo mới thành công |

## Giao diện chức năng
Biểu mẫu thông tin tác giả và nút thao tác.

## Mô tả chi tiết giao diện
| STT | Field name | Loại control | Mô tả |
|---|---|---|---|
| 1 | Tên đầy đủ | Textbox | Bắt buộc; tối đa 120 ký tự |
| 2 | Bút danh | Textbox | Không bắt buộc; tối đa 80 ký tự |
| 3 | Quốc gia | Textbox | Không bắt buộc; tối đa 100 ký tự |
| 4 | Mô tả ngắn | Textarea | Không bắt buộc; tối đa 500 ký tự |
| 5 | Trạng thái | Dropdown chọn một | Mặc định Đang dùng |
| 6 | Lưu | Button | Kiểm tra hợp lệ và lưu dữ liệu |
| 7 | Hủy | Button | Đóng màn hình tạo mới |
