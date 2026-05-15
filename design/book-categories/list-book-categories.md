# Tên chức năng: Danh sách loại sách

## Mục đích

Giúp thủ thư tra cứu loại sách, theo dõi trạng thái sử dụng và số sách thuộc từng loại.

## Trigger

Người dùng mở menu **Danh mục loại sách**.

## Tiền điều kiện

Người dùng đã đăng nhập.

## Hậu điều kiện

Không thay đổi dữ liệu.

## Sơ đồ thực hiện

Mở màn hình -> Nhập điều kiện tìm -> Chọn tìm kiếm -> Hiển thị danh sách -> Chọn thao tác tại từng dòng.

## Quy tắc nghiệp vụ

1. Hỗ trợ tìm theo tên loại sách.
2. Hỗ trợ lọc theo trạng thái Đang dùng/Ngừng dùng.
3. Hiển thị số sách hiện có theo từng loại để kiểm soát dữ liệu.

## Bước thực hiện

| Bước | Chi tiết                                                           |
|------|--------------------------------------------------------------------|
| 1    | Người dùng truy cập danh sách loại sách                            |
| 2    | Hệ thống hiển thị bộ lọc và danh sách mặc định                     |
| 3    | Người dùng nhập từ khóa/chọn trạng thái                            |
| 4    | Người dùng nhấn Tìm kiếm                                           |
| 5    | Hệ thống lọc dữ liệu theo điều kiện                                |
| 6    | Hiển thị kết quả danh sách                                         |
| 7    | Người dùng có thể chọn Chỉnh sửa hoặc Ngừng sử dụng theo từng dòng |

## Giao diện chức năng

Gồm khu vực điều kiện tra cứu, bảng danh sách và các nút thao tác nhanh.

## Mô tả chi tiết giao diện

| STT | Field name       | Loại control      | Mô tả                                                                          |
|-----|------------------|-------------------|--------------------------------------------------------------------------------|
| 1   | keyword          | Textbox           | Nhập tên loại sách cần tìm                                                     |
| 2   | status           | Dropdown chọn một | Đang dùng/Ngừng dùng                                                           |
| 3   | searchButton     | Button            | Thực hiện lọc dữ liệu                                                          |
| 4   | resetButton      | Button            | Xóa điều kiện lọc về mặc định                                                  |
| 5   | resultsTable     | Grid              | Hiển thị: Tên loại sách, Mô tả, Số sách hiện có, Trạng thái, Cập nhật gần nhất |
| 6   | editAction       | Action tại dòng   | Mở màn hình cập nhật loại sách                                                 |
| 7   | deactivateAction | Action tại dòng   | Mở xác nhận ngừng sử dụng                                                      |
