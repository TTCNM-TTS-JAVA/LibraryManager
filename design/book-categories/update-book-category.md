# Tên chức năng: Cập nhật loại sách

## Mục đích
Cho phép chỉnh sửa thông tin loại sách để phù hợp thực tế vận hành.

## Trigger
Người dùng chọn hành động **Chỉnh sửa** tại một dòng loại sách trong danh sách.

## Tiền điều kiện
1. Loại sách đã tồn tại.
2. Người dùng có quyền cập nhật danh mục.

## Hậu điều kiện
Thông tin loại sách được cập nhật thành công.

## Sơ đồ thực hiện
Mở danh sách -> Chọn chỉnh sửa -> Cập nhật thông tin -> Lưu -> Kiểm tra hợp lệ -> Hoàn tất.

## Quy tắc nghiệp vụ
1. Không cho đổi tên thành tên đang được dùng ở loại sách khác.
2. Trường bắt buộc không được để trống.
3. Nếu loại sách đã ngừng sử dụng, việc cập nhật tuân theo chính sách của đơn vị.

## Bước thực hiện
| Bước | Chi tiết |
|---|---|
| 1 | Người dùng chọn Chỉnh sửa tại danh sách |
| 2 | Hệ thống hiển thị thông tin hiện tại |
| 3 | Người dùng thay đổi thông tin |
| 4 | Người dùng nhấn Lưu |
| 5 | Hệ thống kiểm tra dữ liệu và ràng buộc trùng |
| 6 | Nếu lỗi, hiển thị thông báo tại trường liên quan |
| 7 | Nếu hợp lệ, hệ thống ghi nhận thay đổi |
| 8 | Hiển thị thông báo cập nhật thành công |

## Giao diện chức năng
Biểu mẫu tương tự tạo mới nhưng hiển thị dữ liệu đã có.

## Mô tả chi tiết giao diện
| STT | Field name | Loại control | Mô tả |
|---|---|---|---|
| 1 | categoryName | Textbox | Bắt buộc; tối đa 100 ký tự; không trùng với bản ghi khác |
| 2 | description | Textarea | Không bắt buộc; tối đa 500 ký tự |
| 3 | status | Dropdown chọn một | Đang dùng/Ngừng dùng |
| 4 | saveButton | Button | Kiểm tra và lưu thay đổi |
| 5 | cancelButton | Button | Thoát không lưu |
