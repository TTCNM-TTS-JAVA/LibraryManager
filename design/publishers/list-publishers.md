# Tên chức năng: Danh sách nhà xuất bản

## Mục đích
Tra cứu nhà xuất bản và theo dõi số lượng sách theo từng nhà xuất bản.

## Trigger
Người dùng mở menu **Danh mục nhà xuất bản**.

## Tiền điều kiện
Người dùng đã đăng nhập.

## Hậu điều kiện
Không thay đổi dữ liệu.

## Sơ đồ thực hiện
Mở danh sách -> Nhập điều kiện -> Tìm kiếm -> Xem kết quả -> Chọn thao tác.

## Quy tắc nghiệp vụ
1. Hỗ trợ tìm theo tên nhà xuất bản.
2. Hỗ trợ lọc theo trạng thái.
3. Hiển thị số đầu sách theo từng nhà xuất bản.

## Bước thực hiện
| Bước | Chi tiết |
|---|---|
| 1 | Người dùng mở màn hình danh sách |
| 2 | Hệ thống hiển thị dữ liệu mặc định |
| 3 | Người dùng nhập điều kiện tìm kiếm |
| 4 | Người dùng chọn Tìm kiếm |
| 5 | Hệ thống hiển thị kết quả |
| 6 | Người dùng chọn Chỉnh sửa/Ngừng sử dụng tại từng dòng |

## Giao diện chức năng
Bộ lọc và bảng kết quả danh sách nhà xuất bản.

## Mô tả chi tiết giao diện
| STT | Field name | Loại control | Mô tả |
|---|---|---|---|
| 1 | keyword | Textbox | Tìm theo tên nhà xuất bản |
| 2 | status | Dropdown chọn một | Đang dùng/Ngừng dùng |
| 3 | searchButton | Button | Lọc dữ liệu |
| 4 | resetButton | Button | Xóa điều kiện tìm |
| 5 | resultsTable | Grid | Hiển thị: Tên NXB, Email, Số điện thoại, Số đầu sách, Trạng thái |
| 6 | editAction | Action tại dòng | Mở màn hình cập nhật |
| 7 | deactivateAction | Action tại dòng | Mở xác nhận ngừng sử dụng |
