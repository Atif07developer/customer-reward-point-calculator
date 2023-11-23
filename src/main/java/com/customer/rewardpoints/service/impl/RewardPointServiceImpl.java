package com.customer.rewardpoints.service.impl;

import com.customer.rewardpoints.dto.MonthlyRewardDTO;
import com.customer.rewardpoints.model.Customer;
import com.customer.rewardpoints.model.Transaction;
import com.customer.rewardpoints.repo.TransactionRepository;
import com.customer.rewardpoints.service.RewardPointService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class RewardPointServiceImpl implements RewardPointService {

    private final TransactionRepository transactionRepository;
    private static final int DURATION = 2;
    private static final int START_DAY_OF_MONTH = 1;

    private static final Logger logger = LoggerFactory.getLogger(RewardPointServiceImpl.class);

    public RewardPointServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    /**
     * <p>
     *     calculateRewardPoints() is to calculate total rewards point of the given customer for the last three months
     * </p>
     * @param customer the customer's object
     * @return total reward points for the last three months
     */
    @Override
    public int calculateRewardPoints(Customer customer) {
        try {
            logger.info("Calculating total reward point for the customer");
            LocalDate threeMonthBackFromNow = LocalDate.now().minusMonths(DURATION);
            LocalDate startDate = LocalDate.of(threeMonthBackFromNow.getYear(), threeMonthBackFromNow.getMonth(), START_DAY_OF_MONTH);
            return transactionRepository.findAllByCustomerAndTransactionDateGreaterThanEqual(customer, startDate)
                    .stream()
                    .mapToInt(this::calculatePointsForTransaction)
                    .sum();
        } catch (DateTimeParseException e) {
            logger.error("Error occurred while calculating total reward points for customer");
            throw new IllegalArgumentException("Error calculating reward points", e);
        }
    }

    /**
     * <p>
     *     calculateMonthlyRewardPoints() is to calculate monthly reward point of the given customer
     * </p>
     * @param customer the customer's object
     * @return monthly reward point
     */
    @Override
    public List<MonthlyRewardDTO> calculateMonthlyRewardPoints(Customer customer) {
        try {
            logger.info("Calculating monthly reward point for the customer");
            LocalDate threeMonthBackFromNow = LocalDate.now().minusMonths(DURATION);
            LocalDate startDate = LocalDate.of(threeMonthBackFromNow.getYear(), threeMonthBackFromNow.getMonth(), START_DAY_OF_MONTH);

            return transactionRepository.findAllByCustomerAndTransactionDateGreaterThanEqual(customer, startDate)
                    .stream()
                    .collect(Collectors.groupingBy(this::getMonthName,
                            Collectors.summingInt(this::calculatePointsForTransaction)))
                    .entrySet()
                    .stream()
                    .map(entry -> new MonthlyRewardDTO(entry.getKey(), entry.getValue()))
                    .collect(Collectors.toList());
        } catch (DateTimeParseException e) {
            logger.error("Error occurred while calculating monthly reward points for customer");
            throw new IllegalArgumentException("Error calculating monthly reward points", e);
        }
    }

    /**
     * <p>
     *     calculatePointsForTransaction() is to calculate the reward points for the given transaction
     * </p>
     * @param transaction the transaction's object
     * @return reward point
     */
    private int calculatePointsForTransaction(Transaction transaction) {
        logger.info("Calculating reward point for the transaction");
        double amount = transaction.getAmount();

        // Calculate points based on the given criteria
        int points = 0;
        if (amount > 50) {
            points += (int) (amount - 50);
        }
        if (amount > 100) {
            points += (int) (amount - 100);
        }

        return points;
    }

    private String getMonthName(Transaction transaction) {
        return transaction.getTransactionDate().getMonth().name();
    }
}