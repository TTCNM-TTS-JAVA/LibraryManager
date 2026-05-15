# Tên chức năng: Cập nhật nhà xuất bản

## Mục đích

Cập nhật thông tin nhà xuất bản theo tình hình thực tế.

## Trigger

Người dùng chọn **Chỉnh sửa** tại danh sách nhà xuất bản.

## Tiền điều kiện

1. Nhà xuất bản đã tồn tại.
2. Người dùng có quyền cập nhật danh mục.

## Hậu điều kiện

Thông tin nhà xuất bản được cập nhật.

## Sơ đồ thực hiện

Mở danh sách -> Chọn chỉnh sửa -> Cập nhật thông tin -> Lưu -> Kiểm tra hợp lệ -> Hoàn tất.

## Quy tắc nghiệp vụ

1. Không cho đổi tên thành tên đã tồn tại ở bản ghi đang dùng.
2. Trường bắt buộc không được để trống.
3. Nếu dữ liệu liên hệ không đúng định dạng thì không cho lưu.

## Bước thực hiện

| Bước | Chi tiết                               |
|------|----------------------------------------|
| 1    | Người dùng chọn Chỉnh sửa              |
| 2    | Hệ thống hiển thị dữ liệu hiện tại     |
| 3    | Người dùng thay đổi thông tin          |
| 4    | Người dùng chọn Lưu                    |
| 5    | Hệ thống kiểm tra dữ liệu              |
| 6    | Nếu lỗi, hiển thị lỗi theo từng trường |
| 7    | Nếu hợp lệ, hệ thống lưu thay đổi      |
| 8    | Thông báo cập nhật thành công          |

## Giao diện chức năng

Biểu mẫu chỉnh sửa nhà xuất bản.

## Mô tả chi tiết giao diện

| STT | Field name    | Loại control      | Mô tả                                   |
|-----|---------------|-------------------|-----------------------------------------|
| 1   | publisherName | Textbox           | Bắt buộc; tối đa 150 ký tự; không trùng |
| 2   | contactEmail  | Textbox           | Không bắt buộc; đúng định dạng email    |
| 3   | phoneNumber   | Textbox           | Không bắt buộc; tối đa 20 ký tự         |
| 4   | address       | Textarea          | Không bắt buộc; tối đa 500 ký tự        |
| 5   | status        | Dropdown chọn một | Đang dùng/Ngừng dùng                    |
| 6   | saveButton    | Button            | Kiểm tra và lưu thay đổi                |
| 7   | cancelButton  | Button            | Thoát không lưu                         |
