package com.dasangwan.pickit.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class LoginDto {
    String email;
    String password;
//    boolean isAutoLogin;
}
