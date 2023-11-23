package com.customer.rewardpoints.service;

import com.customer.rewardpoints.dto.MonthlyRewardDTO;
import com.customer.rewardpoints.model.Customer;
import com.customer.rewardpoints.model.Transaction;
import com.customer.rewardpoints.repo.TransactionRepository;
import com.customer.rewardpoints.service.impl.RewardPointServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class RewardPointServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private RewardPointServiceImpl rewardPointService;

    private static final String DATE_FORMAT = "MM/dd/yyyy";

    private final Customer johnSr = new Customer("John Sr", "999999999", "john@gmail.com");
    private final Customer tomJr = new Customer("Tom Jr", "999999999", "tom@gmail.com");
    private final Customer mikeJr = new Customer("Mike Jr", "999999999", "mike@gmail.com");

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void calculateRewardPoints() {
        when(transactionRepository.findAllByCustomerAndTransactionDateGreaterThanEqual(johnSr, LocalDate.of(2023, 9, 1)))
                .thenReturn(Arrays.asList(
                        new Transaction(1200, LocalDate.parse("11/21/2023", DateTimeFormatter.ofPattern(DATE_FORMAT)), johnSr),
                        new Transaction(200, LocalDate.parse("11/11/2023", DateTimeFormatter.ofPattern(DATE_FORMAT)), johnSr),
                        new Transaction(1100, LocalDate.parse("03/10/2023", DateTimeFormatter.ofPattern(DATE_FORMAT)), tomJr),
                        new Transaction(2200, LocalDate.parse("11/18/2023", DateTimeFormatter.ofPattern(DATE_FORMAT)), mikeJr)
                ));

        int totalRewardPoints = rewardPointService.calculateRewardPoints(johnSr);
        assertEquals(8800, totalRewardPoints);
    }

    @Test
    void calculateMonthlyRewardPoints() {
        when(transactionRepository.findAllByCustomerAndTransactionDateGreaterThanEqual(johnSr, LocalDate.of(2023, 9, 1)))
                .thenReturn(Arrays.asList(
                        new Transaction(1200, LocalDate.parse("11/21/2023", DateTimeFormatter.ofPattern(DATE_FORMAT)), johnSr),
                        new Transaction(200, LocalDate.parse("09/11/2023", DateTimeFormatter.ofPattern(DATE_FORMAT)), johnSr),
                        new Transaction(1100, LocalDate.parse("03/10/2023", DateTimeFormatter.ofPattern(DATE_FORMAT)), tomJr),
                        new Transaction(2200, LocalDate.parse("11/18/2023", DateTimeFormatter.ofPattern(DATE_FORMAT)), mikeJr)
                ));

        List<MonthlyRewardDTO> monthlyRewardPoints = rewardPointService.calculateMonthlyRewardPoints(johnSr);
        assertEquals(3, monthlyRewardPoints.size());

        MonthlyRewardDTO novemberReward = monthlyRewardPoints.stream()
                .filter(monthlyRewardDTO -> monthlyRewardDTO.getMonthName().equals("NOVEMBER"))
                .findFirst().orElse(null);

        MonthlyRewardDTO septemberReward = monthlyRewardPoints.stream()
                .filter(monthlyRewardDTO -> monthlyRewardDTO.getMonthName().equals("SEPTEMBER"))
                .findFirst().orElse(null);

        assertEquals("NOVEMBER", novemberReward.getMonthName());
        assertEquals(6500, novemberReward.getMonthRewardPoint());

        assertEquals("SEPTEMBER", septemberReward.getMonthName());
        assertEquals(250, septemberReward.getMonthRewardPoint());
    }
}
