package com.fr.exalt.infra.adapter;

import java.util.List;
import java.util.Optional;

import com.fr.exalt.core.domain.Client;
import com.fr.exalt.core.port.secondary.ClientRepository;
import com.fr.exalt.infra.mapper.ClientMapper;
import com.fr.exalt.infra.repository.ClientJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class ClientRepositoryAdapter implements ClientRepository {
    private final ClientJpaRepository clientJpaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Client> findAllClients() {
        return clientJpaRepository.findAll().stream()
                .map(ClientMapper::mapperFromClientEntity)
                .toList();
    }

    @Override
    @Transactional
    public Client saveClient(Client client) {
        var clientEntity = ClientMapper.mapperToClientEntity(client);
        var clientEntitySaved = clientJpaRepository.save(clientEntity);
        return ClientMapper.mapperFromClientEntity(clientEntitySaved);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Client> findClientById(Long id) {
        return clientJpaRepository.findById(id)
                .map(ClientMapper::mapperFromClientEntity);
    }

    @Override
    @Transactional
    public void deleteClientById(Long id) {
        clientJpaRepository.deleteById(id);
    }
    
}
