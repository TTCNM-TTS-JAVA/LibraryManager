# Tên chức năng: Cập nhật tác giả

## Mục đích
Chỉnh sửa thông tin tác giả để dữ liệu đồng nhất và đúng thực tế.

## Trigger
Người dùng chọn **Chỉnh sửa** tại danh sách tác giả.

## Tiền điều kiện
1. Tác giả đã tồn tại.
2. Người dùng có quyền chỉnh sửa.

## Hậu điều kiện
Thông tin tác giả được cập nhật.

## Sơ đồ thực hiện
Mở danh sách -> Chọn chỉnh sửa -> Cập nhật thông tin -> Lưu -> Kiểm tra hợp lệ -> Hoàn tất.

## Quy tắc nghiệp vụ
1. Tên đầy đủ không được trùng với tác giả khác đang dùng.
2. Nếu để trống trường bắt buộc thì không cho lưu.
3. Việc đổi trạng thái phải theo đúng quyền của người dùng.

## Bước thực hiện
| Bước | Chi tiết |
|---|---|
| 1 | Người dùng chọn tác giả cần chỉnh sửa |
| 2 | Hệ thống hiển thị dữ liệu hiện tại |
| 3 | Người dùng cập nhật thông tin |
| 4 | Người dùng chọn Lưu |
| 5 | Hệ thống kiểm tra dữ liệu |
| 6 | Nếu lỗi, hiển thị lỗi cụ thể |
| 7 | Nếu hợp lệ, hệ thống ghi nhận thay đổi |
| 8 | Hiển thị thông báo cập nhật thành công |

## Giao diện chức năng
Biểu mẫu chỉnh sửa tương tự biểu mẫu tạo mới.

## Mô tả chi tiết giao diện
| STT | Field name | Loại control | Mô tả |
|---|---|---|---|
| 1 | Tên đầy đủ | Textbox | Bắt buộc; tối đa 120 ký tự; không trùng |
| 2 | Bút danh | Textbox | Không bắt buộc; tối đa 80 ký tự |
| 3 | Quốc gia | Textbox | Không bắt buộc; tối đa 100 ký tự |
| 4 | Mô tả ngắn | Textarea | Không bắt buộc; tối đa 500 ký tự |
| 5 | Trạng thái | Dropdown chọn một | Đang dùng/Ngừng dùng |
| 6 | Lưu | Button | Lưu thông tin sau kiểm tra |
| 7 | Hủy | Button | Thoát không lưu |
