# Tên chức năng: Ngừng sử dụng tác giả

## Mục đích
Ngừng dùng tác giả không còn áp dụng nhưng vẫn giữ lịch sử dữ liệu.

## Trigger
Người dùng chọn **Ngừng sử dụng** tại danh sách tác giả.

## Tiền điều kiện
Tác giả không còn gắn với sách đang dùng.

## Hậu điều kiện
Tác giả chuyển trạng thái Ngừng dùng.

## Sơ đồ thực hiện
Chọn ngừng sử dụng -> Kiểm tra ràng buộc -> Xác nhận -> Cập nhật trạng thái -> Thông báo.

## Quy tắc nghiệp vụ
1. Không cho ngừng sử dụng nếu tác giả còn gắn với sách đang dùng.
2. Bắt buộc xác nhận trước khi thực hiện.
3. Sau khi ngừng sử dụng, tác giả không hiển thị trong danh sách chọn khi tạo sách mới.

## Bước thực hiện
| Bước | Chi tiết |
|---|---|
| 1 | Người dùng chọn Ngừng sử dụng tác giả |
| 2 | Hệ thống kiểm tra dữ liệu liên quan |
| 3 | Nếu không đạt điều kiện, hiển thị lý do từ chối |
| 4 | Nếu đạt điều kiện, mở hộp xác nhận |
| 5 | Người dùng xác nhận thao tác |
| 6 | Hệ thống cập nhật trạng thái |
| 7 | Hiển thị thông báo ngừng sử dụng thành công |

## Giao diện chức năng
Hộp xác nhận ngừng sử dụng tác giả.

## Mô tả chi tiết giao diện
| STT | Field name | Loại control | Mô tả |
|---|---|---|---|
| 1 | Lý do ngừng dùng | Textarea | Không bắt buộc; tối đa 500 ký tự |
| 2 | Đồng ý | Button | Xác nhận ngừng sử dụng |
| 3 | Hủy | Button | Hủy thao tác |
