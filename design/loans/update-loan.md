# Tên chức năng: Cập nhật phiếu mượn

## Mục đích
Xử lý các thao tác nghiệp vụ trong quá trình mượn trả: gia hạn, chốt trả, cập nhật trạng thái.

## Trigger
Người dùng chọn **Cập nhật** tại danh sách phiếu mượn.

## Tiền điều kiện
1. Phiếu mượn đã tồn tại.
2. Người dùng có quyền xử lý phiếu mượn.

## Hậu điều kiện
Thông tin phiếu mượn được cập nhật theo nghiệp vụ thực tế.

## Sơ đồ thực hiện
Mở phiếu -> Chỉnh thông tin cần cập nhật -> Lưu -> Kiểm tra điều kiện -> Hoàn tất.

## Quy tắc nghiệp vụ
1. Trạng thái chỉ chuyển theo luồng hợp lệ.
2. Khi chốt **Đã trả**, hệ thống cập nhật lại số lượng khả dụng của sách.
3. Khi gia hạn, hạn trả mới không được nhỏ hơn hạn trả hiện tại.

## Bước thực hiện
| Bước | Chi tiết |
|---|---|
| 1 | Người dùng mở phiếu cần cập nhật |
| 2 | Hệ thống hiển thị thông tin hiện tại |
| 3 | Người dùng chỉnh hạn trả/trạng thái/ngày trả thực tế |
| 4 | Người dùng chọn Lưu |
| 5 | Hệ thống kiểm tra ràng buộc nghiệp vụ |
| 6 | Nếu lỗi, hiển thị thông báo tương ứng |
| 7 | Nếu hợp lệ, hệ thống ghi nhận thay đổi |
| 8 | Thông báo cập nhật thành công |

## Giao diện chức năng
Biểu mẫu cập nhật trạng thái phiếu mượn.

## Mô tả chi tiết giao diện
| STT | Field name | Loại control | Mô tả |
|---|---|---|---|
| 1 | Hạn trả mới | Date picker | Không bắt buộc; dùng khi gia hạn |
| 2 | Ngày trả thực tế | Date picker | Không bắt buộc; dùng khi chốt trả |
| 3 | Trạng thái phiếu | Dropdown chọn một | Bắt buộc; Đang mượn/Đã trả/Quá hạn |
| 4 | Ghi chú xử lý | Textarea | Không bắt buộc; tối đa 500 ký tự |
| 5 | Lưu | Button | Lưu thay đổi sau kiểm tra |
| 6 | Hủy | Button | Thoát không lưu |
