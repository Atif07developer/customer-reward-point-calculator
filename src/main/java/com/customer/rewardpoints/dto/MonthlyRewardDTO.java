package com.customer.rewardpoints.dto;

public class MonthlyRewardDTO {
    private String monthName;
    private int monthRewardPoint;

    public MonthlyRewardDTO(String monthName, int monthRewardPoint) {
        this.monthName = monthName;
        this.monthRewardPoint = monthRewardPoint;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public int getMonthRewardPoint() {
        return monthRewardPoint;
    }

    public void setMonthRewardPoint(int monthRewardPoint) {
        this.monthRewardPoint = monthRewardPoint;
    }
}
