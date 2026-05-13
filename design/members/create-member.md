# Tên chức năng: Tạo mới độc giả

## Mục đích
Tạo hồ sơ độc giả đầy đủ để phục vụ quản lý mượn trả sách.

## Trigger
Người dùng mở màn hình **Độc giả** và chọn **Tạo mới**.

## Tiền điều kiện
1. Người dùng đã đăng nhập.
2. Người dùng có quyền quản lý độc giả.

## Hậu điều kiện
Độc giả mới được thêm vào danh sách và sẵn sàng cho nghiệp vụ mượn sách.

## Sơ đồ thực hiện
Mở danh mục độc giả -> Tạo mới -> Nhập thông tin -> Lưu -> Kiểm tra hợp lệ -> Hoàn tất.

## Quy tắc nghiệp vụ
1. Mã độc giả là bắt buộc và không trùng.
2. Họ và tên là bắt buộc.
3. Trạng thái mặc định của độc giả mới là **Đang hoạt động**.
4. Nếu email được nhập thì phải đúng định dạng.

## Bước thực hiện
| Bước | Chi tiết |
|---|---|
| 1 | Người dùng chọn Tạo mới độc giả |
| 2 | Hệ thống hiển thị biểu mẫu |
| 3 | Người dùng nhập thông tin |
| 4 | Người dùng chọn Lưu |
| 5 | Hệ thống kiểm tra dữ liệu bắt buộc và định dạng |
| 6 | Nếu lỗi, hiển thị thông báo tại trường tương ứng |
| 7 | Nếu hợp lệ, hệ thống ghi nhận dữ liệu |
| 8 | Hiển thị thông báo tạo mới thành công |

## Giao diện chức năng
Biểu mẫu nhập thông tin độc giả.

## Mô tả chi tiết giao diện
| STT | Field name | Loại control | Mô tả |
|---|---|---|---|
| 1 | Mã độc giả | Textbox | Bắt buộc; tối đa 20 ký tự; không trùng |
| 2 | Họ và tên | Textbox | Bắt buộc; tối đa 120 ký tự |
| 3 | Email | Textbox | Không bắt buộc; đúng định dạng email |
| 4 | Số điện thoại | Textbox | Không bắt buộc; tối đa 20 ký tự |
| 5 | Ngày sinh | Date picker | Không bắt buộc; ngày hợp lệ |
| 6 | Địa chỉ | Textarea | Không bắt buộc; tối đa 500 ký tự |
| 7 | Trạng thái | Dropdown chọn một | Mặc định Đang hoạt động |
| 8 | Lưu | Button | Kiểm tra và lưu dữ liệu |
| 9 | Hủy | Button | Đóng màn hình, không lưu |
