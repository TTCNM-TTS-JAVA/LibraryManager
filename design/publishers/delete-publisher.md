# Tên chức năng: Ngừng sử dụng nhà xuất bản

## Mục đích

Ngừng dùng nhà xuất bản không còn áp dụng, bảo đảm dữ liệu lịch sử vẫn đầy đủ.

## Trigger

Người dùng chọn **Ngừng sử dụng** tại danh sách nhà xuất bản.

## Tiền điều kiện

Nhà xuất bản không còn sách đang dùng.

## Hậu điều kiện

Nhà xuất bản được chuyển trạng thái Ngừng dùng.

## Sơ đồ thực hiện

Chọn ngừng sử dụng -> Kiểm tra ràng buộc -> Xác nhận -> Cập nhật trạng thái.

## Quy tắc nghiệp vụ

1. Không cho ngừng sử dụng khi còn sách đang dùng liên quan.
2. Bắt buộc xác nhận trước khi thực hiện.
3. Sau khi ngừng dùng, nhà xuất bản không hiển thị trong danh sách chọn khi tạo sách mới.

## Bước thực hiện

| Bước | Chi tiết                                   |
|------|--------------------------------------------|
| 1    | Người dùng chọn Ngừng sử dụng              |
| 2    | Hệ thống kiểm tra dữ liệu liên quan        |
| 3    | Nếu không hợp lệ, hiển thị thông báo lý do |
| 4    | Nếu hợp lệ, mở hộp xác nhận                |
| 5    | Người dùng xác nhận thao tác               |
| 6    | Hệ thống cập nhật trạng thái               |
| 7    | Hiển thị thông báo thành công              |

## Giao diện chức năng

Hộp xác nhận ngừng sử dụng nhà xuất bản.

## Mô tả chi tiết giao diện

| STT | Field name         | Loại control | Mô tả                            |
|-----|--------------------|--------------|----------------------------------|
| 1   | deactivationReason | Textarea     | Không bắt buộc; tối đa 500 ký tự |
| 2   | confirmButton      | Button       | Xác nhận ngừng sử dụng           |
| 3   | cancelButton       | Button       | Hủy thao tác                     |
