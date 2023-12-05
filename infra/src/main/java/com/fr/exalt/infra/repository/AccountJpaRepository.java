package com.fr.exalt.infra.repository;

import java.util.List;

import com.fr.exalt.infra.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountJpaRepository extends JpaRepository<AccountEntity, Long> {
    List<AccountEntity> findAccountsByClientEntity_Id(Long clientId);

}
