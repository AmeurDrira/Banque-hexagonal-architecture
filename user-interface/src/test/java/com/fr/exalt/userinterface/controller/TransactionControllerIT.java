package com.fr.exalt.userinterface.controller;

import java.math.BigDecimal;

import com.fr.exalt.core.domain.TransactionType;
import com.fr.exalt.userinterface.dto.RequestTransactionDto;
import com.fr.exalt.infra.repository.AccountJpaRepository;
import com.fr.exalt.infra.repository.ClientJpaRepository;
import com.fr.exalt.infra.repository.TransactionJpaRepository;
import com.fr.exalt.userinterface.config.AbstractIntegrationTest;
import com.fr.exalt.userinterface.data.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TransactionControllerIT extends AbstractIntegrationTest {

    private static final String URL_TRANSACTION = "/api/transaction";

    @Autowired
    private AccountJpaRepository accountRepository;

    @Autowired
    private ClientJpaRepository clientRepository;

    @Autowired
    private TransactionJpaRepository transactionRepository;


    @BeforeEach
    void setUpAll() {
        transactionRepository.deleteAll();
        accountRepository.deleteAll();
        clientRepository.deleteAll();

        clientRepository.saveAll(Data.getClients());
        accountRepository.saveAll(Data.getAccounts());
        transactionRepository.saveAll(Data.getTransactions());
    }

    @Test
    void return_list_transaction_dto_when_get_transaction_by_client() throws Exception {
        final RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URL_TRANSACTION + "/client/1")
                .accept(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void return_transaction_when_find_transaction_by_id() throws Exception {
        final RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URL_TRANSACTION + "/2")
                .accept(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.clientId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.clientFirstName").value("ervan"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.clientLastName").value("salin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.transactionId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.transactionType").value("RETRAIT"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.transactionAmount").value(10.00))
                .andExpect(MockMvcResultMatchers.jsonPath("$.transactionBalanceAfter").value(500.00))
                .andExpect(MockMvcResultMatchers.jsonPath("$.transactionDateTime").value("2023-11-30T14:30:00"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void throw_not_found_exception_when_get_transaction_by_id() throws Exception {
        final RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URL_TRANSACTION + "/999")
                .accept(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(requestBuilder).andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.title", is("Not Found")))
                .andExpect(jsonPath("$.detail", is("Not found: Transaction 999 not exist.")))
                .andExpect(jsonPath("$.status", is(404)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void return_transaction_dto_when_deposit() throws Exception {
        var transactionDto = RequestTransactionDto.builder()
                .accountReceiverId(1L)
                .amount(BigDecimal.valueOf(200))
                .type(TransactionType.DEPOT)
                .build();

        final RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL_TRANSACTION)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writerWithDefaultPrettyPrinter()
                        .writeValueAsString(transactionDto))
                .contentType(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.clientId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.clientFirstName").value("ervan"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.clientLastName").value("salin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.transactionId").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.transactionType").value("DEPOT"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.transactionAmount").value(200.00))
                .andExpect(MockMvcResultMatchers.jsonPath("$.transactionBalanceAfter").value(1550.00))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void return_transaction_dto_when_deposit_with_USD() throws Exception {
        var transactionDto = RequestTransactionDto.builder()
                .accountReceiverId(1L)
                .amount(BigDecimal.valueOf(100))
                .type(TransactionType.DEPOT)
                .build();

        final RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL_TRANSACTION)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writerWithDefaultPrettyPrinter()
                        .writeValueAsString(transactionDto))
                .contentType(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.transactionAmount").value(100.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.transactionBalanceAfter").value(1450))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void return_transaction_dto_when_withdrawal() throws Exception {
        var transactionDto = RequestTransactionDto.builder()
                .accountReceiverId(1L)
                .amount(BigDecimal.valueOf(100))
                .type(TransactionType.RETRAIT)
                .build();

        final RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL_TRANSACTION)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writerWithDefaultPrettyPrinter()
                        .writeValueAsString(transactionDto))
                .contentType(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.clientId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.clientFirstName").value("ervan"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.clientLastName").value("salin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.transactionId").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.transactionType").value("RETRAIT"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.transactionAmount").value(100.00))
                .andExpect(MockMvcResultMatchers.jsonPath("$.transactionBalanceAfter").value(1250.00))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void throw_transaction_exception_when_account_overdrafted() throws Exception {
        var transactionDto = RequestTransactionDto.builder()
                .accountReceiverId(1L)
                .amount(BigDecimal.valueOf(2000))
                .type(TransactionType.RETRAIT)
                .build();

        final RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL_TRANSACTION)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writerWithDefaultPrettyPrinter()
                        .writeValueAsString(transactionDto))
                .contentType(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(requestBuilder).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title", is("Bad Request")))
                .andExpect(jsonPath("$.detail", is("Transaction refused: The account will be in bank overdraft -650.00")))
                .andExpect(jsonPath("$.status", is(400)))
                .andDo(MockMvcResultHandlers.print());
    }
}
