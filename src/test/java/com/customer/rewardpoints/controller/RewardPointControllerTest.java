package com.customer.rewardpoints.controller;

import com.customer.rewardpoints.repo.CustomerRepository;
import com.customer.rewardpoints.repo.TransactionRepository;
import com.customer.rewardpoints.service.RewardPointService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class RewardPointControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private TransactionRepository transactionRepository;

    @MockBean
    private RewardPointService rewardPointService;

    @BeforeEach
    void init(){
        Mockito.when(customerRepository.saveAll(ArgumentMatchers.anyIterable())).thenReturn(new ArrayList<>());
        Mockito.when(transactionRepository.saveAll(ArgumentMatchers.anyIterable())).thenReturn(new ArrayList<>());
        Mockito.when(customerRepository.findAll()).thenReturn(new ArrayList<>());
    }

    @Test
    void getAllRewardPoints_success() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/getAllRewardPoints")).andReturn();
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
    }

}