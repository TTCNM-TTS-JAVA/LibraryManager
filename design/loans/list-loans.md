# Tên chức năng: Danh sách phiếu mượn

## Mục đích
Theo dõi tình trạng mượn trả, phát hiện phiếu quá hạn và hỗ trợ tra cứu lịch sử.

## Trigger
Người dùng mở menu **Phiếu mượn**.

## Tiền điều kiện
Người dùng đã đăng nhập.

## Hậu điều kiện
Không thay đổi dữ liệu.

## Sơ đồ thực hiện
Mở danh sách -> Nhập điều kiện lọc -> Tìm kiếm -> Xem kết quả -> Chọn thao tác chi tiết/cập nhật.

## Quy tắc nghiệp vụ
1. Hỗ trợ lọc theo trạng thái phiếu.
2. Hỗ trợ tìm theo thông tin độc giả.
3. Hỗ trợ lọc theo khoảng thời gian mượn.

## Bước thực hiện
| Bước | Chi tiết |
|---|---|
| 1 | Người dùng mở màn hình danh sách phiếu mượn |
| 2 | Hệ thống hiển thị bộ lọc mặc định |
| 3 | Người dùng nhập điều kiện tìm kiếm |
| 4 | Người dùng chọn Tìm kiếm |
| 5 | Hệ thống hiển thị danh sách kết quả |
| 6 | Người dùng chọn cập nhật/hủy phiếu theo từng dòng khi cần |

## Giao diện chức năng
Gồm bộ lọc và bảng danh sách phiếu mượn.

## Mô tả chi tiết giao diện
| STT | Field name | Loại control | Mô tả |
|---|---|---|---|
| 1 | Mã hoặc tên độc giả | Textbox | Tìm nhanh theo thông tin độc giả |
| 2 | Trạng thái phiếu | Dropdown chọn một | Đang mượn/Đã trả/Quá hạn |
| 3 | Từ ngày | Date picker | Lọc từ ngày mượn |
| 4 | Đến ngày | Date picker | Lọc đến ngày mượn |
| 5 | Tìm kiếm | Button | Lọc danh sách |
| 6 | Làm mới | Button | Xóa điều kiện lọc |
| 7 | Bảng danh sách | Grid | Hiển thị: Mã phiếu, Độc giả, Ngày mượn, Hạn trả, Số đầu sách, Trạng thái |
| 8 | Cập nhật | Action tại dòng | Mở màn hình cập nhật phiếu |
| 9 | Hủy phiếu | Action tại dòng | Mở xác nhận hủy phiếu |

## Nguồn dữ liệu cho bộ lọc liên quan
1. Thông tin độc giả: đối chiếu từ danh mục Độc giả.
2. Trạng thái phiếu: sử dụng bộ giá trị trạng thái phiếu mượn.
