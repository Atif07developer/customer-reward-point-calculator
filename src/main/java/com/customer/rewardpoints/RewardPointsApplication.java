package com.customer.rewardpoints;

import com.customer.rewardpoints.component.DataInitializer;
import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RewardPointsApplication {

	private final DataInitializer dataInitializer;

	public RewardPointsApplication(DataInitializer dataInitializer) {
		this.dataInitializer = dataInitializer;
	}

	public static void main(String[] args) {
		SpringApplication.run(RewardPointsApplication.class, args);
	}

	@PostConstruct
	public void initializeData () {
		dataInitializer.initializeData();
	}
}
