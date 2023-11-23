package com.customer.rewardpoints.repo;

import com.customer.rewardpoints.model.Customer;
import com.customer.rewardpoints.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByCustomerAndTransactionDateGreaterThanEqual(Customer customer, LocalDate localDate);
}
