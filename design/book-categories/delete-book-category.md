# Tên chức năng: Ngừng sử dụng loại sách

## Mục đích

Ngừng dùng loại sách không còn áp dụng, đồng thời giữ lịch sử dữ liệu.

## Trigger

Người dùng chọn hành động **Ngừng sử dụng** tại danh sách loại sách.

## Tiền điều kiện

Loại sách không còn sách đang dùng liên quan.

## Hậu điều kiện

Loại sách chuyển trạng thái **Ngừng dùng**.

## Sơ đồ thực hiện

Chọn ngừng sử dụng -> Kiểm tra dữ liệu liên quan -> Xác nhận -> Cập nhật trạng thái.

## Quy tắc nghiệp vụ

1. Nếu loại sách còn sách đang dùng thì không cho ngừng sử dụng.
2. Bắt buộc xác nhận thao tác trước khi thực hiện.
3. Sau khi ngừng sử dụng, loại sách không còn hiển thị trong danh sách chọn khi tạo sách mới.

## Bước thực hiện

| Bước | Chi tiết                                           |
|------|----------------------------------------------------|
| 1    | Người dùng chọn Ngừng sử dụng                      |
| 2    | Hệ thống kiểm tra ràng buộc dữ liệu liên quan      |
| 3    | Nếu không hợp lệ, hiển thị thông báo lý do         |
| 4    | Nếu hợp lệ, hiển thị hộp xác nhận                  |
| 5    | Người dùng nhập lý do (nếu có) và xác nhận         |
| 6    | Hệ thống cập nhật trạng thái                       |
| 7    | Hiển thị thông báo thành công và làm mới danh sách |

## Giao diện chức năng

Hộp xác nhận ngừng sử dụng.

## Mô tả chi tiết giao diện

| STT | Field name         | Loại control | Mô tả                            |
|-----|--------------------|--------------|----------------------------------|
| 1   | deactivationReason | Textarea     | Không bắt buộc; tối đa 500 ký tự |
| 2   | confirmButton      | Button       | Xác nhận ngừng sử dụng           |
| 3   | cancelButton       | Button       | Hủy thao tác, giữ nguyên dữ liệu |
