package com.fr.exalt.infra.mapper;

import com.fr.exalt.core.domain.Client;
import com.fr.exalt.infra.entity.ClientEntity;

public class ClientMapper {

    public static Client mapperFromClientEntity(ClientEntity clientEntity) {
        return Client.builder()
                .firstName(clientEntity.getFirstName())
                .id(clientEntity.getId())
                .lastName(clientEntity.getLastName())
                .build();
    }

    public static ClientEntity mapperToClientEntity(Client client) {
        return ClientEntity.builder()
                .firstName(client.getFirstName())
                .id(client.getId())
                .lastName(client.getLastName())
                .build();
    }
}
