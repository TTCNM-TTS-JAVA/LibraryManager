# Tên chức năng: Cập nhật sách

## Mục đích
Chỉnh sửa thông tin sách khi có thay đổi về phân loại, tác giả, nhà xuất bản hoặc số lượng.

## Trigger
Người dùng chọn **Chỉnh sửa** tại danh sách sách.

## Tiền điều kiện
1. Sách đã tồn tại.
2. Người dùng có quyền cập nhật dữ liệu sách.

## Hậu điều kiện
Thông tin sách được cập nhật thành công.

## Sơ đồ thực hiện
Mở danh sách -> Chọn chỉnh sửa -> Cập nhật thông tin -> Lưu -> Kiểm tra hợp lệ -> Hoàn tất.

## Quy tắc nghiệp vụ
1. Mã sách không được trùng với sách khác.
2. Sách luôn phải có loại sách, nhà xuất bản và ít nhất một tác giả.
3. Số lượng tổng không được nhỏ hơn số lượng đang được mượn.

## Bước thực hiện
| Bước | Chi tiết |
|---|---|
| 1 | Người dùng chọn Chỉnh sửa sách |
| 2 | Hệ thống hiển thị thông tin hiện tại |
| 3 | Người dùng thay đổi dữ liệu |
| 4 | Người dùng chọn Lưu |
| 5 | Hệ thống kiểm tra ràng buộc dữ liệu |
| 6 | Nếu lỗi, hiển thị lỗi chi tiết |
| 7 | Nếu hợp lệ, hệ thống ghi nhận cập nhật |
| 8 | Hiển thị thông báo cập nhật thành công |

## Giao diện chức năng
Biểu mẫu chỉnh sửa giống biểu mẫu tạo mới.

## Mô tả chi tiết giao diện
| STT | Field name | Loại control | Mô tả |
|---|---|---|---|
| 1 | bookCode | Textbox | Bắt buộc; không trùng |
| 2 | bookTitle | Textbox | Bắt buộc; tối đa 200 ký tự |
| 3 | categoryId | Dropdown chọn một | Bắt buộc; chọn từ Loại sách đang dùng |
| 4 | authorIds | Dropdown chọn nhiều | Bắt buộc; ít nhất một tác giả |
| 5 | publisherId | Dropdown chọn một | Bắt buộc; chọn từ Nhà xuất bản đang dùng |
| 6 | publishedYear | Textbox số | Không bắt buộc; 1900 đến năm hiện tại |
| 7 | totalQuantity | Textbox số | Bắt buộc; số nguyên dương và không thấp hơn số đang mượn |
| 8 | shelfLocation | Textbox | Không bắt buộc |
| 9 | status | Dropdown chọn một | Đang dùng/Ngừng dùng |
| 10 | saveButton | Button | Lưu thay đổi sau kiểm tra |
| 11 | cancelButton | Button | Thoát không lưu |

## Nguồn dữ liệu cho các trường liên quan
1. Loại sách: lấy từ danh mục Loại sách đang dùng.
2. Tác giả: lấy từ danh mục Tác giả đang dùng.
3. Nhà xuất bản: lấy từ danh mục Nhà xuất bản đang dùng.
