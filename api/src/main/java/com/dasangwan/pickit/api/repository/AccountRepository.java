package com.dasangwan.pickit.api.repository;

import com.dasangwan.pickit.api.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByEmail(String email);
}
