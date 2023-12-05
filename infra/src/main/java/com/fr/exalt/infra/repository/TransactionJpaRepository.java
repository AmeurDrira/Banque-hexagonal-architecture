package com.fr.exalt.infra.repository;

import java.util.List;

import com.fr.exalt.infra.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionJpaRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findTransactionEntitiesByClient_Id(Long id);
}
