# Tên chức năng: Tạo phiếu mượn

## Mục đích

Ghi nhận nghiệp vụ mượn sách của độc giả trong một lần giao dịch.

## Trigger

Người dùng mở màn hình **Phiếu mượn** và chọn **Tạo mới**.

## Tiền điều kiện

1. Độc giả đang hoạt động.
2. Sách được chọn đang dùng và còn số lượng khả dụng.
3. Người dùng có quyền tạo phiếu mượn.

## Hậu điều kiện

Phiếu mượn được tạo thành công và số lượng khả dụng của sách được cập nhật tương ứng.

## Sơ đồ thực hiện

Tạo phiếu -> Chọn độc giả -> Chọn danh sách sách -> Nhập hạn trả -> Lưu -> Kiểm tra điều kiện -> Hoàn tất.

## Quy tắc nghiệp vụ

1. Mỗi phiếu mượn bắt buộc chọn một độc giả.
2. Mỗi phiếu mượn phải có ít nhất một dòng sách.
3. Số lượng mượn từng dòng phải lớn hơn 0 và không vượt số khả dụng.
4. Hạn trả không được nhỏ hơn ngày mượn.
5. Trạng thái ban đầu của phiếu là **Đang mượn**.

## Bước thực hiện

| Bước | Chi tiết                                                                |
|------|-------------------------------------------------------------------------|
| 1    | Người dùng chọn Tạo mới phiếu mượn                                      |
| 2    | Hệ thống hiển thị biểu mẫu tạo phiếu                                    |
| 3    | Người dùng chọn độc giả, ngày mượn, hạn trả                             |
| 4    | Người dùng thêm từng dòng sách mượn                                     |
| 5    | Người dùng nhấn Lưu                                                     |
| 6    | Hệ thống kiểm tra dữ liệu bắt buộc, số lượng khả dụng và ràng buộc ngày |
| 7    | Nếu lỗi, hiển thị lỗi tại dòng/ trường tương ứng                        |
| 8    | Nếu hợp lệ, hệ thống ghi nhận phiếu mượn                                |
| 9    | Thông báo tạo phiếu mượn thành công                                     |

## Giao diện chức năng

Biểu mẫu đầu phiếu và bảng chi tiết sách mượn nhiều dòng.

## Mô tả chi tiết giao diện

| STT | Field name   | Loại control      | Mô tả                                            |
|-----|--------------|-------------------|--------------------------------------------------|
| 1   | memberId     | Dropdown chọn một | Bắt buộc; lấy từ danh mục Độc giả đang hoạt động |
| 2   | borrowDate   | Date picker       | Bắt buộc; mặc định ngày hiện tại                 |
| 3   | dueDate      | Date picker       | Bắt buộc; không nhỏ hơn ngày mượn                |
| 4   | loanItems    | Bảng nhiều dòng   | Bắt buộc; gồm trường Sách và Số lượng mượn       |
| 5   | note         | Textarea          | Không bắt buộc; tối đa 500 ký tự                 |
| 6   | loanStatus   | Dropdown chọn một | Mặc định Đang mượn                               |
| 7   | saveButton   | Button            | Kiểm tra hợp lệ và lưu                           |
| 8   | cancelButton | Button            | Thoát không lưu                                  |

## Mô tả chi tiết từng dòng sách mượn

| STT | Field name | Loại control      | Mô tả                                              |
|-----|------------|-------------------|----------------------------------------------------|
| 1   | bookId     | Dropdown chọn một | Bắt buộc; chỉ hiển thị sách đang dùng còn khả dụng |
| 2   | quantity   | Textbox số        | Bắt buộc; số nguyên > 0; không vượt số khả dụng    |

## Nguồn dữ liệu cho các trường liên quan

1. Độc giả: lấy từ danh mục Độc giả đang hoạt động.
2. Sách: lấy từ danh mục Sách đang dùng và còn số lượng khả dụng.
