package com.dasangwan.pickit.api.dto;

import com.dasangwan.pickit.api.domain.IdpType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class AccountSignUpDto {
    private String email;
    private String password;
    private String nickname;
    private String phoneNumber;
    private IdpType idpType;
}
