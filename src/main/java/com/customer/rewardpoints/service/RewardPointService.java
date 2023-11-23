package com.customer.rewardpoints.service;

import com.customer.rewardpoints.dto.MonthlyRewardDTO;
import com.customer.rewardpoints.model.Customer;

import java.util.List;

public interface RewardPointService {
    int calculateRewardPoints(Customer customer);
    List<MonthlyRewardDTO> calculateMonthlyRewardPoints(Customer customer);
}
