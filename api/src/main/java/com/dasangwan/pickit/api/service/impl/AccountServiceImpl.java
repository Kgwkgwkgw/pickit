package com.dasangwan.pickit.api.service.impl;

import com.dasangwan.pickit.api.Exception.AlreadyRegisterdException;
import com.dasangwan.pickit.api.Exception.NotRegisteredException;
import com.dasangwan.pickit.api.Exception.WrongPasswordException;
import com.dasangwan.pickit.api.domain.Account;
import com.dasangwan.pickit.api.dto.AccountSignUpDto;
import com.dasangwan.pickit.api.dto.LoginDto;
import com.dasangwan.pickit.api.repository.AccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class AccountServiceImpl {

    private AccountRepository accountRepository;
    private PasswordEncoder passwordEncoder;

    public AccountServiceImpl (AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Account login (LoginDto loginDto) {
        String email = loginDto.getEmail();
        Account found = Optional.ofNullable(accountRepository.findByEmail(email))
                .orElseThrow(NotRegisteredException::new);

        if (!this.passwordEncoder.matches(loginDto.getPassword(), found.getPassword())) {
            throw new WrongPasswordException();
        }

        return found;
    }

    public Account register (AccountSignUpDto accountSignUpDto) {
        String email = accountSignUpDto.getEmail();

        if(Optional.ofNullable(accountRepository.findByEmail(email)).isPresent()) {
            throw new AlreadyRegisterdException();
        }

        Account account = Account.builder()
                                .email(accountSignUpDto.getEmail())
                                .password(passwordEncoder.encode(accountSignUpDto.getPassword()))
                                .nickname(accountSignUpDto.getNickname())
                                .phoneNumber(accountSignUpDto.getPhoneNumber())
                                .idpType(accountSignUpDto.getIdpType())
                                .updatedAt(Instant.now())
                                .createdAt(Instant.now())
                                .build();
        return accountRepository.save(account);
    }
}
