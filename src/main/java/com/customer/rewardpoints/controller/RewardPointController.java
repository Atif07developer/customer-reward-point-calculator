package com.customer.rewardpoints.controller;

import com.customer.rewardpoints.dto.CustomerDTO;
import com.customer.rewardpoints.dto.MonthlyRewardDTO;
import com.customer.rewardpoints.dto.RewardResponseDTO;
import com.customer.rewardpoints.model.Customer;
import com.customer.rewardpoints.repo.CustomerRepository;
import com.customer.rewardpoints.service.RewardPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class RewardPointController {

    private final CustomerRepository customerRepository;
    private final RewardPointService rewardPointService;

    private static final Logger logger = LoggerFactory.getLogger(RewardPointController.class);

    @Autowired
    public RewardPointController(CustomerRepository customerRepository, RewardPointService rewardPointService) {
        this.customerRepository = customerRepository;
        this.rewardPointService = rewardPointService;
    }

    /**
     * <p>
     *     This is API endpoint method that returns array of json objects where
     *     each json object is to show the total reward points and the last three
     *     month's reward point of a customer
     * </p>
     * @return the array of json objects where each json object is to show the
     * total reward points and the last three months reward point of a customer
     */
    @GetMapping("/getAllRewardPoints")
    public ResponseEntity<List<CustomerDTO>> getAllRewardPoints() {
        try {
            logger.info("Calculating reward points for all customers for the past three months");
            List<CustomerDTO> customerDTOs = customerRepository.findAll()
                    .stream()
                    .map(this::mapToCustomerDTO)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(customerDTOs, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error while getting all reward points: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * <p>
     *     The mapToCustomerDTO is to prepare the response json
     * </p>
     */
    private CustomerDTO mapToCustomerDTO(Customer customer) {
        logger.info("Executing mapToCustomerDTO");
        int totalRewardPoints = rewardPointService.calculateRewardPoints(customer);
        List<MonthlyRewardDTO> monthlyRecords = rewardPointService
                .calculateMonthlyRewardPoints(customer)
                .stream()
                .map(monthlyRecord -> new MonthlyRewardDTO(monthlyRecord.getMonthName(), monthlyRecord.getMonthRewardPoint()))
                .collect(Collectors.toList());

        RewardResponseDTO rewardResponseDTO = new RewardResponseDTO(totalRewardPoints, monthlyRecords);

        return new CustomerDTO(customer.getId(), customer.getCustomerName(), rewardResponseDTO);
    }
}
