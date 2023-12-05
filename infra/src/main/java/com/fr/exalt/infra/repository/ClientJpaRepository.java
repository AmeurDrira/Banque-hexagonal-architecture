package com.fr.exalt.infra.repository;

import com.fr.exalt.infra.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientJpaRepository extends JpaRepository<ClientEntity, Long> {
}
