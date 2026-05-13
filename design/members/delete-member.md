# Tên chức năng: Ngừng sử dụng độc giả

## Mục đích
Ngừng sử dụng hồ sơ độc giả không còn hiệu lực.

## Trigger
Người dùng chọn **Ngừng sử dụng** tại danh sách độc giả.

## Tiền điều kiện
Độc giả không có phiếu đang mượn.

## Hậu điều kiện
Độc giả chuyển trạng thái **Ngừng hoạt động**.

## Sơ đồ thực hiện
Chọn ngừng sử dụng -> Kiểm tra điều kiện -> Xác nhận -> Cập nhật trạng thái.

## Quy tắc nghiệp vụ
1. Không cho ngừng sử dụng nếu độc giả còn sách chưa trả.
2. Bắt buộc xác nhận thao tác.
3. Sau khi ngừng hoạt động, độc giả không còn xuất hiện trong danh sách chọn khi tạo phiếu mượn mới.

## Bước thực hiện
| Bước | Chi tiết |
|---|---|
| 1 | Người dùng chọn Ngừng sử dụng độc giả |
| 2 | Hệ thống kiểm tra tình trạng mượn hiện tại |
| 3 | Nếu không đạt điều kiện, hiển thị thông báo lý do |
| 4 | Nếu hợp lệ, mở hộp xác nhận |
| 5 | Người dùng xác nhận thao tác |
| 6 | Hệ thống cập nhật trạng thái |
| 7 | Hiển thị thông báo thành công |

## Giao diện chức năng
Hộp xác nhận ngừng sử dụng độc giả.

## Mô tả chi tiết giao diện
| STT | Field name | Loại control | Mô tả |
|---|---|---|---|
| 1 | Lý do ngừng hoạt động | Textarea | Không bắt buộc; tối đa 500 ký tự |
| 2 | Đồng ý | Button | Xác nhận ngừng sử dụng |
| 3 | Hủy | Button | Hủy thao tác |
