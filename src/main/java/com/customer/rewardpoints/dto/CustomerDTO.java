package com.customer.rewardpoints.dto;

public class CustomerDTO {
    private long customerId;
    private String customerName;
    private RewardResponseDTO rewardPoint;

    public CustomerDTO(long customerId, String customerName, RewardResponseDTO rewardPoint) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.rewardPoint = rewardPoint;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public RewardResponseDTO getRewardPoint() {
        return rewardPoint;
    }

    public void setRewardPoint(RewardResponseDTO rewardPoint) {
        this.rewardPoint = rewardPoint;
    }
}
