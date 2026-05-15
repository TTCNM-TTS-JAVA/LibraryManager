# Tên chức năng: Cập nhật độc giả

## Mục đích

Điều chỉnh thông tin độc giả khi có thay đổi thực tế.

## Trigger

Người dùng chọn **Chỉnh sửa** tại danh sách độc giả.

## Tiền điều kiện

1. Độc giả đã tồn tại.
2. Người dùng có quyền chỉnh sửa.

## Hậu điều kiện

Thông tin độc giả được cập nhật.

## Sơ đồ thực hiện

Mở danh sách -> Chọn chỉnh sửa -> Cập nhật thông tin -> Lưu -> Kiểm tra hợp lệ -> Hoàn tất.

## Quy tắc nghiệp vụ

1. Mã độc giả không được trùng với người khác.
2. Các trường bắt buộc không được để trống.
3. Độc giả đang có phiếu mượn có thể bị giới hạn một số thay đổi theo quy định đơn vị.

## Bước thực hiện

| Bước | Chi tiết                                |
|------|-----------------------------------------|
| 1    | Người dùng chọn bản ghi độc giả cần sửa |
| 2    | Hệ thống hiển thị thông tin hiện tại    |
| 3    | Người dùng thay đổi thông tin           |
| 4    | Người dùng nhấn Lưu                     |
| 5    | Hệ thống kiểm tra tính hợp lệ           |
| 6    | Nếu lỗi, hiển thị lỗi chi tiết          |
| 7    | Nếu hợp lệ, ghi nhận cập nhật           |
| 8    | Hiển thị thông báo cập nhật thành công  |

## Giao diện chức năng

Biểu mẫu cập nhật độc giả.

## Mô tả chi tiết giao diện

| STT | Field name   | Loại control      | Mô tả                                |
|-----|--------------|-------------------|--------------------------------------|
| 1   | memberCode   | Textbox           | Bắt buộc; không trùng                |
| 2   | fullName     | Textbox           | Bắt buộc; tối đa 120 ký tự           |
| 3   | Email        | Textbox           | Không bắt buộc; đúng định dạng email |
| 4   | phoneNumber  | Textbox           | Không bắt buộc; tối đa 20 ký tự      |
| 5   | dateOfBirth  | Date picker       | Không bắt buộc                       |
| 6   | address      | Textarea          | Không bắt buộc; tối đa 500 ký tự     |
| 7   | status       | Dropdown chọn một | Đang hoạt động/Ngừng hoạt động       |
| 8   | saveButton   | Button            | Lưu thay đổi                         |
| 9   | cancelButton | Button            | Thoát không lưu                      |
