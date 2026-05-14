# Tên chức năng: Tạo mới nhà xuất bản

## Mục đích
Thêm nhà xuất bản vào danh mục để sử dụng khi tạo và cập nhật sách.

## Trigger
Người dùng mở màn hình **Danh mục nhà xuất bản** và chọn **Tạo mới**.

## Tiền điều kiện
1. Người dùng đã đăng nhập.
2. Người dùng có quyền quản lý danh mục.

## Hậu điều kiện
Nhà xuất bản mới xuất hiện trong danh sách và có thể chọn trong màn hình sách.

## Sơ đồ thực hiện
Mở danh mục -> Tạo mới -> Nhập thông tin -> Lưu -> Kiểm tra hợp lệ -> Hoàn tất.

## Quy tắc nghiệp vụ
1. Tên nhà xuất bản là bắt buộc.
2. Không cho tạo trùng tên nhà xuất bản đang dùng.
3. Email và số điện thoại là thông tin liên hệ, không bắt buộc.

## Bước thực hiện
| Bước | Chi tiết |
|---|---|
| 1 | Người dùng chọn Tạo mới nhà xuất bản |
| 2 | Hệ thống hiển thị biểu mẫu |
| 3 | Người dùng nhập thông tin |
| 4 | Người dùng chọn Lưu |
| 5 | Hệ thống kiểm tra ràng buộc dữ liệu |
| 6 | Nếu lỗi, hiển thị lỗi theo từng trường |
| 7 | Nếu hợp lệ, ghi nhận dữ liệu |
| 8 | Thông báo tạo mới thành công |

## Giao diện chức năng
Biểu mẫu nhập thông tin nhà xuất bản.

## Mô tả chi tiết giao diện
| STT | Field name | Loại control | Mô tả |
|---|---|---|---|
| 1 | publisherName | Textbox | Bắt buộc; tối đa 150 ký tự |
| 2 | contactEmail | Textbox | Không bắt buộc; đúng định dạng email |
| 3 | phoneNumber | Textbox | Không bắt buộc; tối đa 20 ký tự |
| 4 | address | Textarea | Không bắt buộc; tối đa 500 ký tự |
| 5 | status | Dropdown chọn một | Mặc định Đang dùng |
| 6 | saveButton | Button | Kiểm tra hợp lệ và lưu |
| 7 | cancelButton | Button | Thoát không lưu |
