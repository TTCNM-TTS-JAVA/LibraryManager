package org.library.manager.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.library.manager.enums.Status;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FindByFullName {
    private String fullName;
    private String penName;
    private Status status;
}
