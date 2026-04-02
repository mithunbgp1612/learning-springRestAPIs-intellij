package com.mhcoder.dto;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
}
