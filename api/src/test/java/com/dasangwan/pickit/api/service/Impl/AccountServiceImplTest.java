package com.dasangwan.pickit.api.service.Impl;

import com.dasangwan.pickit.api.Exception.AlreadyRegisterdException;
import com.dasangwan.pickit.api.Exception.NotRegisteredException;
import com.dasangwan.pickit.api.Exception.WrongPasswordException;
import com.dasangwan.pickit.api.domain.Account;
import com.dasangwan.pickit.api.domain.IdpType;
import com.dasangwan.pickit.api.dto.AccountSignUpDto;
import com.dasangwan.pickit.api.dto.LoginDto;
import com.dasangwan.pickit.api.repository.AccountRepository;
import com.dasangwan.pickit.api.service.impl.AccountServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AccountServiceImpl.class}) // 빈으로 등록하고자하는 클래스들을 명시한다.(명시하지않으면 모든 클래스 로드)
public class AccountServiceImplTest {
    @Autowired
    private AccountServiceImpl accountService;
    @MockBean // application bean이 아니라 mockBean을 주입한다.
    private AccountRepository accountRepository;
    @MockBean
    private PasswordEncoder passwordEncoder;

    private LoginDto loginDto;
    private AccountSignUpDto accountSignUpDto;
    private Account account;
    @Before
    public void setUp () {
        loginDto = loginDto.builder()
                .email("kgwaaa@naver.com")
                .password("pw")
                .build();

        String email = "kgwaaa@naver.com";
        String password = "pw";
        IdpType idpType = IdpType.KAKAO;
        String nickname = "tommy";
        String phoneNumber ="01000000000";

        accountSignUpDto = accountSignUpDto.builder()
                .email(email)
                .password(password)
                .idpType(idpType)
                .nickname(nickname)
                .phoneNumber(phoneNumber)
                .build();
        account = account.builder()
                .email(email)
                .password(password)
                .idpType(idpType)
                .nickname(nickname)
                .phoneNumber(phoneNumber)
                .build();
    }

    @Test
    public void 회원가입_정상_케이스 () {
        // given
        given(accountRepository.findByEmail(accountSignUpDto.getEmail())).willReturn(null);

        // when
        accountService.register(accountSignUpDto);
        // then
        verify(accountRepository, times(1)).save(any());
    }

    @Test(expected = AlreadyRegisterdException.class)
    public void 회원가입할_때_이메일_중복이면_에러_발생 () {
        // given
        given(accountRepository.findByEmail(accountSignUpDto.getEmail())).willReturn(account);

        //when
        accountService.register(accountSignUpDto);
    }
    @Test(expected = NotRegisteredException.class)
    public void 로그인할_때_등록된_아이디가_없으면_에러_발생 () {
        // given
        given(accountRepository.findByEmail(loginDto.getEmail())).willReturn(null);

        //when
        accountService.login(loginDto);
    }
    @Test(expected = WrongPasswordException.class)
    public void 로그인할_때_비밀번호가_일치하지_않으면_에러_발생 () {
        // given
        given(accountRepository.findByEmail(loginDto.getEmail())).willReturn(account);
        given(passwordEncoder.matches(loginDto.getPassword(), account.getPassword())).willReturn(false);

        //when
        accountService.login(loginDto);
    }
    @Test
    public void 로그인_정상_케이스 () {
        // given
        given(accountRepository.findByEmail(loginDto.getEmail())).willReturn(account);
        given(passwordEncoder.matches(loginDto.getPassword(), account.getPassword())).willReturn(true);

        //when
        Account loginnedUser = accountService.login(loginDto);
        assertThat(account, samePropertyValuesAs(loginnedUser));
    }
}
