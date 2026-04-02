package com.mhcoder.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin {

    private String email;
    private String password;
}
