package com.customer.rewardpoints.dto;
import java.util.List;

public class RewardResponseDTO {
    private int totalRewardPoint;
    private List<MonthlyRewardDTO> monthlyRewardPoint;

    public RewardResponseDTO(int totalRewardPoint, List<MonthlyRewardDTO> monthlyRewardPoint) {
        this.totalRewardPoint = totalRewardPoint;
        this.monthlyRewardPoint = monthlyRewardPoint;
    }
    public int getTotalRewardPoint() {
        return totalRewardPoint;
    }

    public void setTotalRewardPoint(int totalRewardPoint) {
        this.totalRewardPoint = totalRewardPoint;
    }

    public List<MonthlyRewardDTO> getMonthlyRewardPoint() {
        return monthlyRewardPoint;
    }

    public void setMonthlyRewardPoint(List<MonthlyRewardDTO> monthlyRewardPoint) {
        this.monthlyRewardPoint = monthlyRewardPoint;
    }
}