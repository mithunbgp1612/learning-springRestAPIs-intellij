package com.mhcoder.dto;

import com.mhcoder.models.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin {

    private String email;
    private String password;

    private Role role;
}
