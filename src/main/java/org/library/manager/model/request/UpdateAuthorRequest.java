package org.library.manager.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.library.manager.enums.Status;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateAuthorRequest {

    @NotBlank(message = "Tên tác giả không được để trống")
    @Size(max = 120, message = "Tên tác giả không được vượt quá 120 ký tự")
    String fullName;

    @Size(max = 80, message = "Bút danh không được vượt quá 80 ký tự")
    String penName;

    @Size(max = 100, message = "Quốc gia không được vượt quá 100 ký tự")
    String country;

    @Size(max = 500, message = "Mô tả không được vượt quá 500 ký tự")
    String description;

    @NotNull(message = "Trạng thái không được để trống")
    Status status;

    @Size(max = 500, message = "Lý do ngừng hoạt động không được vượt quá 500 ký tự")
    String inactiveReason;
}