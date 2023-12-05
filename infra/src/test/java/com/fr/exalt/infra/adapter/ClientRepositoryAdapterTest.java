package com.fr.exalt.infra.adapter;

import java.util.List;
import java.util.Optional;

import com.fr.exalt.core.domain.Client;
import com.fr.exalt.infra.data.Data;
import com.fr.exalt.infra.repository.ClientJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientRepositoryAdapterTest {

    @Mock
    private ClientJpaRepository repository;

    @InjectMocks
    private ClientRepositoryAdapter clientRepositoryAdapter;


    @Test
    void return_list_client_when_find_all() {

        when(repository.findAll()).thenReturn(Data.getClients());
        List<Client> clientList = clientRepositoryAdapter.findAllClients();
        verify(repository, times(1)).findAll();
        assertEquals(3, clientList.size());
    }

    @Test
    void return_client_when_save_client() {
        when(repository.save(any())).thenReturn(Data.getClients().get(0));
        Client client = clientRepositoryAdapter
                .saveClient(Client.builder()
                        .firstName("ervan")
                        .lastName("salin")
                        .build());
        verify(repository, times(1)).save(any());
        assertNotNull(client.getId());
        assertEquals("ervan", client.getFirstName());
        assertEquals("salin", client.getLastName());
    }

    @Test
    void return_optional_when_find_client_by_id() {
        when(repository.findById(2L)).thenReturn(Optional.of(Data.getClients().get(1)));
        var client = clientRepositoryAdapter
                .findClientById(2L);
        verify(repository, times(1)).findById(2L);
        assertTrue(client.isPresent());
        assertEquals("jean-paul", client.get().getFirstName());
        assertEquals("gaultier", client.get().getLastName());
    }

    @Test
    void do_not_return_when_delete_client_by_id() {
        doNothing().when(repository).deleteById(1L);
        clientRepositoryAdapter.deleteClientById(1L);
        verify(repository, times(1)).deleteById(1L);
    }
}