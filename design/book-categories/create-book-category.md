# Tên chức năng: Tạo mới loại sách

## Mục đích

Cho phép thủ thư tạo loại sách mới để chuẩn hóa việc phân nhóm sách trong toàn thư viện.

## Trigger

Người dùng truy cập màn hình **Danh mục loại sách** và chọn nút **Tạo mới**.

## Tiền điều kiện

1. Người dùng đã đăng nhập.
2. Người dùng có quyền quản lý danh mục.

## Hậu điều kiện

1. Loại sách mới được thêm vào danh sách.
2. Loại sách mới có thể được chọn khi tạo/cập nhật sách.

## Sơ đồ thực hiện

Mở danh sách loại sách -> Chọn tạo mới -> Nhập thông tin -> Lưu -> Kiểm tra hợp lệ -> Ghi nhận thành công.

## Quy tắc nghiệp vụ

1. Tên loại sách là bắt buộc.
2. Tên loại sách không phân biệt hoa/thường khi kiểm tra trùng.
3. Không cho lưu nếu tên đã tồn tại ở bản ghi đang dùng.
4. Khi tạo mới, trạng thái mặc định là **Đang dùng**.

## Bước thực hiện

| Bước | Chi tiết                                                    |
|------|-------------------------------------------------------------|
| 1    | Người dùng mở màn hình Danh mục loại sách                   |
| 2    | Người dùng chọn nút Tạo mới                                 |
| 3    | Hệ thống hiển thị biểu mẫu tạo mới                          |
| 4    | Người dùng nhập thông tin và chọn Lưu                       |
| 5    | Hệ thống kiểm tra dữ liệu bắt buộc và ràng buộc trùng       |
| 6    | Nếu dữ liệu chưa hợp lệ, hiển thị lỗi tại trường tương ứng  |
| 7    | Nếu hợp lệ, hệ thống ghi nhận dữ liệu                       |
| 8    | Hiển thị thông báo tạo mới thành công và quay lại danh sách |

## Giao diện chức năng

Biểu mẫu nhập liệu gồm thông tin loại sách và các nút thao tác.

## Mô tả chi tiết giao diện

| STT | Field name   | Loại control      | Mô tả                                                                              |
|-----|--------------|-------------------|------------------------------------------------------------------------------------|
| 1   | categoryName | Textbox           | Bắt buộc; tối đa 100 ký tự; nếu để trống hiển thị lỗi: "Tên loại sách là bắt buộc" |
| 2   | description  | Textarea          | Không bắt buộc; tối đa 500 ký tự                                                   |
| 3   | status       | Dropdown chọn một | Mặc định Đang dùng; gồm Đang dùng/Ngừng dùng                                       |
| 4   | saveButton   | Button            | Click để kiểm tra hợp lệ và ghi nhận dữ liệu                                       |
| 5   | cancelButton | Button            | Đóng màn hình tạo mới, không lưu dữ liệu                                           |
