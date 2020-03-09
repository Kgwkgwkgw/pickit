package com.dasangwan.pickit.api.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name="pick_account")
@Builder
@Getter
@Setter
@ToString
public class Account {
    @Id
    @GeneratedValue
    private long id;
    private String email;
    private String nickname;
    @Enumerated(EnumType.STRING)
    IdpType idpType;
    private String password;
    private String phoneNumber;
    Instant createdAt;
    Instant updatedAt;
}
