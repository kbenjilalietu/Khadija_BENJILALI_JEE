package com.benjilali.ebank_backend.repositories;

import com.benjilali.ebank_backend.entities.SavingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingAccountRepository extends JpaRepository<SavingAccount, String> {

}
