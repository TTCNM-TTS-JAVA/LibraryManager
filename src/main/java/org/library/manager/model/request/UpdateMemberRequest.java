package org.library.manager.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.library.manager.enums.Status;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateMemberRequest {

    @NotBlank(message = "Mã thành viên không được để trống")
    @Size(max = 20, message = "Mã thành viên không được vượt quá 20 ký tự")
    String code;

    @NotBlank(message = "Tên thành viên không được để trống")
    @Size(max = 120, message = "Tên thành viên không được vượt quá 120 ký tự")
    String fullName;

    @Email(message = "Email không đúng định dạng")
    @Size(max = 150, message = "Email không được vượt quá 150 ký tự")
    String email;

    @Size(max = 20, message = "Số điện thoại không được vượt quá 20 ký tự")
    String phone;

    LocalDate dateOfBirth;

    @Size(max = 500, message = "Địa chỉ không được vượt quá 500 ký tự")
    String address;

    @NotNull(message = "Trạng thái không được để trống")
    Status status;

    @Size(max = 500, message = "Lý do ngừng hoạt động không được vượt quá 500 ký tự")
    String inactiveReason;
}