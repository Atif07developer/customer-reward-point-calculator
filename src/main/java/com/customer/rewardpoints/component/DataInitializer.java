package com.customer.rewardpoints.component;

import com.customer.rewardpoints.model.Customer;
import com.customer.rewardpoints.model.Transaction;
import com.customer.rewardpoints.repo.CustomerRepository;
import com.customer.rewardpoints.repo.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Component
@Configuration
public class DataInitializer {
    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;

    private static final String DATE_FORMAT = "MM/dd/yyyy";

    @Autowired
    public DataInitializer(CustomerRepository customerRepository, TransactionRepository transactionRepository) {
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
    }

    public void initializeData() {
        // Create customers
        Customer customer1 = new Customer("John Sr", "999999999", "john@gmail.com");
        Customer customer2 = new Customer("Tom Jr", "999999999", "tom@gmail.com");
        Customer customer3 = new Customer("Mike Jr", "999999999", "mike@gmail.com");

        // Save customers to the database
        customerRepository.saveAll(Arrays.asList(customer1, customer2, customer3));

        // Create transactions
        Transaction transaction1 = new Transaction(1200, LocalDate.parse("11/21/2023", DateTimeFormatter.ofPattern(DATE_FORMAT)), customer1);
        Transaction transaction3 = new Transaction(200, LocalDate.parse("11/11/2023", DateTimeFormatter.ofPattern(DATE_FORMAT)), customer1);
        Transaction transaction7 = new Transaction(250, LocalDate.parse("09/23/2023", DateTimeFormatter.ofPattern(DATE_FORMAT)), customer1);
        Transaction transaction10 = new Transaction(250, LocalDate.parse("10/23/2023", DateTimeFormatter.ofPattern(DATE_FORMAT)), customer1);

        Transaction transaction5 = new Transaction(1100, LocalDate.parse("03/10/2023", DateTimeFormatter.ofPattern(DATE_FORMAT)), customer2);
        Transaction transaction2 = new Transaction(1000, LocalDate.parse("11/10/2023", DateTimeFormatter.ofPattern(DATE_FORMAT)), customer2);
        Transaction transaction8 = new Transaction(90, LocalDate.parse("07/21/2023", DateTimeFormatter.ofPattern(DATE_FORMAT)), customer2);

        Transaction transaction4 = new Transaction(2200, LocalDate.parse("11/18/2023", DateTimeFormatter.ofPattern(DATE_FORMAT)), customer3);
        Transaction transaction6 = new Transaction(900, LocalDate.parse("09/01/2023", DateTimeFormatter.ofPattern(DATE_FORMAT)), customer3);
        Transaction transaction9 = new Transaction(1700, LocalDate.parse("06/21/2023", DateTimeFormatter.ofPattern(DATE_FORMAT)), customer3);

        // Save transactions to the database
        transactionRepository.saveAll(Arrays.asList(
                transaction1, transaction2, transaction3, transaction4, transaction5,
                transaction6, transaction7, transaction8, transaction9, transaction10
        ));
    }
}
