# Tên chức năng: Ngừng sử dụng sách

## Mục đích
Ngừng dùng sách không còn lưu hành nhưng vẫn giữ lịch sử mượn trả.

## Trigger
Người dùng chọn **Ngừng sử dụng** tại danh sách sách.

## Tiền điều kiện
Sách không thuộc phiếu đang mượn.

## Hậu điều kiện
Sách chuyển trạng thái **Ngừng dùng**.

## Sơ đồ thực hiện
Chọn ngừng sử dụng -> Kiểm tra tình trạng mượn -> Xác nhận -> Cập nhật trạng thái.

## Quy tắc nghiệp vụ
1. Không cho ngừng sử dụng nếu sách đang được mượn.
2. Bắt buộc xác nhận trước khi thực hiện.
3. Sau khi ngừng dùng, sách không còn hiển thị để tạo phiếu mượn mới.

## Bước thực hiện
| Bước | Chi tiết |
|---|---|
| 1 | Người dùng chọn Ngừng sử dụng sách |
| 2 | Hệ thống kiểm tra tình trạng mượn |
| 3 | Nếu không hợp lệ, hiển thị lý do từ chối |
| 4 | Nếu hợp lệ, mở hộp xác nhận |
| 5 | Người dùng xác nhận thao tác |
| 6 | Hệ thống cập nhật trạng thái |
| 7 | Thông báo ngừng sử dụng thành công |

## Giao diện chức năng
Hộp xác nhận ngừng sử dụng sách.

## Mô tả chi tiết giao diện
| STT | Field name | Loại control | Mô tả |
|---|---|---|---|
| 1 | Lý do ngừng dùng | Textarea | Không bắt buộc; tối đa 500 ký tự |
| 2 | Đồng ý | Button | Xác nhận ngừng sử dụng |
| 3 | Hủy | Button | Hủy thao tác |
