# Tên chức năng: Danh sách sách

## Mục đích
Tra cứu nhanh danh mục sách theo nhiều điều kiện phục vụ vận hành hằng ngày.

## Trigger
Người dùng mở menu **Sách**.

## Tiền điều kiện
Người dùng đã đăng nhập.

## Hậu điều kiện
Không thay đổi dữ liệu.

## Sơ đồ thực hiện
Mở danh sách -> Nhập điều kiện -> Tìm kiếm -> Xem kết quả -> Chọn thao tác.

## Quy tắc nghiệp vụ
1. Tìm theo mã sách hoặc tên sách.
2. Lọc theo loại sách, tác giả, nhà xuất bản, trạng thái.
3. Hiển thị số lượng khả dụng để hỗ trợ quyết định mượn sách.

## Bước thực hiện
| Bước | Chi tiết |
|---|---|
| 1 | Người dùng mở màn hình danh sách sách |
| 2 | Hệ thống hiển thị điều kiện lọc mặc định |
| 3 | Người dùng nhập/chọn điều kiện lọc |
| 4 | Người dùng nhấn Tìm kiếm |
| 5 | Hệ thống hiển thị danh sách kết quả |
| 6 | Người dùng có thể chọn Chỉnh sửa/Ngừng sử dụng tại từng dòng |

## Giao diện chức năng
Gồm khu vực lọc và bảng danh sách sách.

## Mô tả chi tiết giao diện
| STT | Field name | Loại control | Mô tả |
|---|---|---|---|
| 1 | Từ khóa | Textbox | Tìm theo mã sách/tên sách |
| 2 | Loại sách | Dropdown chọn một | Lọc theo loại sách |
| 3 | Tác giả | Dropdown chọn một | Lọc theo tác giả |
| 4 | Nhà xuất bản | Dropdown chọn một | Lọc theo nhà xuất bản |
| 5 | Trạng thái | Dropdown chọn một | Đang dùng/Ngừng dùng |
| 6 | Tìm kiếm | Button | Lọc dữ liệu |
| 7 | Làm mới | Button | Xóa điều kiện tìm |
| 8 | Bảng danh sách | Grid | Hiển thị: Mã sách, Tên sách, Loại sách, Tác giả, NXB, Số lượng tổng, Số lượng khả dụng, Trạng thái |
| 9 | Chỉnh sửa | Action tại dòng | Mở màn hình cập nhật sách |
| 10 | Ngừng sử dụng | Action tại dòng | Mở xác nhận ngừng sử dụng |

## Nguồn dữ liệu cho bộ lọc liên quan
1. Loại sách: lấy từ danh mục Loại sách.
2. Tác giả: lấy từ danh mục Tác giả.
3. Nhà xuất bản: lấy từ danh mục Nhà xuất bản.
