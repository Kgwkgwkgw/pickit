package com.dasangwan.pickit.api.repository;

import com.dasangwan.pickit.api.domain.Account;
import com.dasangwan.pickit.api.domain.IdpType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private AccountRepository accountRepository;
    Account account;

    @Before
    public void setUp() {
        // given
         account = Account.builder()
                .email("kgwaaa@naver.com")
                .nickname("kgw")
                .idpType(IdpType.KAKAO)
                .password("pw")
                .phoneNumber("01000000000")
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }

    @Test
    public void 이메일로_회원_조회 () {

        entityManager.persist(account);
//        entityManager.flush();

        // when
        Account found = accountRepository.findByEmail(account.getEmail());

        // then
        assertThat(found.getEmail())
                .isEqualTo(account.getEmail());
    }
}
