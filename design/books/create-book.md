# Tên chức năng: Tạo mới sách

## Mục đích

Thêm đầu sách mới vào thư viện để phục vụ tra cứu và mượn trả.

## Trigger

Người dùng mở màn hình **Sách** và chọn **Tạo mới**.

## Tiền điều kiện

1. Loại sách, tác giả, nhà xuất bản đã có dữ liệu.
2. Người dùng có quyền quản lý sách.

## Hậu điều kiện

Sách mới được thêm vào danh mục và có thể dùng trong nghiệp vụ mượn.

## Sơ đồ thực hiện

Mở danh mục sách -> Tạo mới -> Nhập thông tin -> Lưu -> Kiểm tra hợp lệ -> Hoàn tất.

## Quy tắc nghiệp vụ

1. Mã sách là bắt buộc và không trùng.
2. Mỗi sách thuộc đúng một loại sách.
3. Mỗi sách phải có ít nhất một tác giả.
4. Mỗi sách thuộc một nhà xuất bản.
5. Số lượng tổng phải là số nguyên dương.

## Bước thực hiện

| Bước | Chi tiết                                                                |
|------|-------------------------------------------------------------------------|
| 1    | Người dùng chọn Tạo mới sách                                            |
| 2    | Hệ thống hiển thị biểu mẫu                                              |
| 3    | Người dùng nhập/chọn thông tin                                          |
| 4    | Người dùng chọn Lưu                                                     |
| 5    | Hệ thống kiểm tra dữ liệu bắt buộc, ràng buộc trùng và giá trị số lượng |
| 6    | Nếu lỗi, hiển thị lỗi tại trường liên quan                              |
| 7    | Nếu hợp lệ, hệ thống ghi nhận dữ liệu                                   |
| 8    | Thông báo tạo mới thành công và quay lại danh sách                      |

## Giao diện chức năng

Biểu mẫu tạo mới sách gồm thông tin cơ bản, thông tin phân loại và thông tin số lượng.

## Mô tả chi tiết giao diện

| STT | Field name    | Loại control        | Mô tả                                             |
|-----|---------------|---------------------|---------------------------------------------------|
| 1   | bookCode      | Textbox             | Bắt buộc; tối đa 20 ký tự; không trùng            |
| 2   | bookTitle     | Textbox             | Bắt buộc; tối đa 200 ký tự                        |
| 3   | categoryId    | Dropdown chọn một   | Bắt buộc; chọn từ danh mục Loại sách đang dùng    |
| 4   | authorIds     | Dropdown chọn nhiều | Bắt buộc; chọn từ danh mục Tác giả đang dùng      |
| 5   | publisherId   | Dropdown chọn một   | Bắt buộc; chọn từ danh mục Nhà xuất bản đang dùng |
| 6   | publishedYear | Textbox số          | Không bắt buộc; từ 1900 đến năm hiện tại          |
| 7   | totalQuantity | Textbox số          | Bắt buộc; số nguyên > 0                           |
| 8   | shelfLocation | Textbox             | Không bắt buộc; tối đa 50 ký tự                   |
| 9   | status        | Dropdown chọn một   | Mặc định Đang dùng                                |
| 10  | saveButton    | Button              | Kiểm tra và lưu dữ liệu                           |
| 11  | cancelButton  | Button              | Đóng màn hình, không lưu                          |

## Nguồn dữ liệu cho các trường liên quan

1. Loại sách: lấy từ danh mục Loại sách đang dùng.
2. Tác giả: lấy từ danh mục Tác giả đang dùng.
3. Nhà xuất bản: lấy từ danh mục Nhà xuất bản đang dùng.
