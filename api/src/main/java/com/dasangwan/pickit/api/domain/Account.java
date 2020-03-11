package com.dasangwan.pickit.api.domain;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name="pick_account")
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Account {
    @Id
    @GeneratedValue
    private long id;
    @Column(unique = true)
    private String email;
    private String nickname;
    @Enumerated(EnumType.STRING)
    IdpType idpType;
    private String password;
    private String phoneNumber;
    Instant createdAt;
    Instant updatedAt;
}
