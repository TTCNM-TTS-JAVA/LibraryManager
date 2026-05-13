# Tên chức năng: Hủy phiếu mượn

## Mục đích
Hủy phiếu mượn được tạo nhầm hoặc không còn hiệu lực xử lý.

## Trigger
Người dùng chọn **Hủy phiếu** tại danh sách phiếu mượn.

## Tiền điều kiện
1. Phiếu mượn đủ điều kiện hủy theo quy định đơn vị.
2. Phiếu chưa hoàn tất trả sách.

## Hậu điều kiện
Phiếu mượn chuyển trạng thái **Hủy** và dữ liệu liên quan được điều chỉnh theo quy định nghiệp vụ.

## Sơ đồ thực hiện
Chọn hủy -> Kiểm tra điều kiện -> Xác nhận + nhập lý do -> Hủy phiếu -> Thông báo.

## Quy tắc nghiệp vụ
1. Không cho hủy phiếu đã hoàn tất trả.
2. Bắt buộc nhập lý do hủy phiếu.
3. Bắt buộc xác nhận thao tác trước khi cập nhật.

## Bước thực hiện
| Bước | Chi tiết |
|---|---|
| 1 | Người dùng chọn Hủy phiếu |
| 2 | Hệ thống kiểm tra điều kiện hủy |
| 3 | Nếu không hợp lệ, hiển thị thông báo lý do |
| 4 | Nếu hợp lệ, hiển thị hộp xác nhận |
| 5 | Người dùng nhập lý do hủy và xác nhận |
| 6 | Hệ thống cập nhật trạng thái phiếu |
| 7 | Hiển thị thông báo hủy thành công |

## Giao diện chức năng
Hộp xác nhận hủy phiếu mượn.

## Mô tả chi tiết giao diện
| STT | Field name | Loại control | Mô tả |
|---|---|---|---|
| 1 | Lý do hủy phiếu | Textarea | Bắt buộc; tối đa 500 ký tự |
| 2 | Đồng ý | Button | Xác nhận hủy phiếu |
| 3 | Hủy | Button | Hủy thao tác |
