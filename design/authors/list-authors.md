# Tên chức năng: Danh sách tác giả

## Mục đích

Tra cứu tác giả và theo dõi mức độ sử dụng tác giả trong danh mục sách.

## Trigger

Người dùng mở menu **Danh mục tác giả**.

## Tiền điều kiện

Người dùng đã đăng nhập.

## Hậu điều kiện

Không thay đổi dữ liệu.

## Sơ đồ thực hiện

Mở danh sách -> Nhập điều kiện lọc -> Tìm kiếm -> Xem kết quả -> Chọn thao tác.

## Quy tắc nghiệp vụ

1. Tìm theo tên đầy đủ hoặc bút danh.
2. Lọc theo trạng thái sử dụng.
3. Hiển thị số đầu sách của từng tác giả.

## Bước thực hiện

| Bước | Chi tiết                                                  |
|------|-----------------------------------------------------------|
| 1    | Người dùng mở màn hình danh sách tác giả                  |
| 2    | Hệ thống hiển thị bộ lọc mặc định                         |
| 3    | Người dùng nhập điều kiện tìm kiếm                        |
| 4    | Người dùng chọn Tìm kiếm                                  |
| 5    | Hệ thống hiển thị kết quả phù hợp                         |
| 6    | Người dùng thao tác Chỉnh sửa/Ngừng sử dụng tại từng dòng |

## Giao diện chức năng

Gồm bộ lọc, bảng kết quả và thao tác tại dòng.

## Mô tả chi tiết giao diện

| STT | Field name       | Loại control      | Mô tả                                                             |
|-----|------------------|-------------------|-------------------------------------------------------------------|
| 1   | keyword          | Textbox           | Tìm theo tên hoặc bút danh                                        |
| 2   | status           | Dropdown chọn một | Đang dùng/Ngừng dùng                                              |
| 3   | searchButton     | Button            | Lọc dữ liệu theo điều kiện                                        |
| 4   | resetButton      | Button            | Xóa điều kiện lọc                                                 |
| 5   | resultsTable     | Grid              | Hiển thị: Tên đầy đủ, Bút danh, Quốc gia, Số đầu sách, Trạng thái |
| 6   | editAction       | Action tại dòng   | Mở màn hình cập nhật tác giả                                      |
| 7   | deactivateAction | Action tại dòng   | Mở hộp xác nhận ngừng sử dụng                                     |
