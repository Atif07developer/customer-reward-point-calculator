# Customer Reward Point Calculator

This is a Spring Boot microservice REST API application that calculates the total rewards and rewards for the last three months of customers whose data is stored in an in-memory H2 database. The application can also be run on Docker containers.

## Steps to run

### Without Docker

To run the application locally, follow the steps below:
1. Clone the repository to your local machine.
2. Open the terminal/command prompt and navigate to the root directory of the project.
3. Build the project by running the command:

   `mvn clean install`
4. Run the application by running the command:

   `mvn spring-boot:run`
5. Check that the application is running by opening the following URL in your browser: http://localhost:8080/actuator/health

   The expected response should be:
   ```json
   
   {
   "status": "UP"
   }

   ```
### With Docker
To run the application on Docker containers, follow the steps below:
1. Clone the repository to your local machine.
2. Open the terminal/command prompt and navigate to the root directory of the project.
3. Build the Docker image by running the command:

   `docker build -t reward-point-app:1.0 .`
4. Check that the Docker image was created by running the command:

   `docker images`
5. Create a container on port 8090 by running the command:

   `docker run -p 8090:8080 --name reward-point-ctr-1 -d  reward-point-app:1.0`
6. Create a container on port 8070 by running the command:

   `docker run -p 8070:8080 --name reward-point-ctr-2 -d  reward-point-app:1.0`
7. Check that the containers are running by opening the following URLs in your browser:
   * Container 1: http://localhost:8090/actuator/health and http://localhost:8090/getAllRewardPoints
   * Container 2: http://localhost:8070/actuator/health and http://localhost:8070/getAllRewardPoints

### Testing
To run the JUnit test cases, follow the steps below:

1. Navigate to the root directory of the project.
2. Run the test cases by running the command

   `mvn test`

### Database
The application uses an in-memory H2 database to store data. The table structures and sample data are as follows:

Database URL: http://localhost:8080/h2-console

#### Customer Table
| customer_id | customer_name |    customer_phone     | customer_email | 
|:-----------:|:---:|:---------------------:|--|
|      1      |    John Sr |       999999999       | john@gmail.com |
|      2      |    Tom Jr |       999999999       | tom@gmail.com  |
|      3      |  Mike Jr   |   999999999           |      mike@gmail.com          |


#### Transaction Table

| transaction_id | customer_id | transaction_amount  | transaction_date  |
|----------------|-------------|---|---|
| 1              | 1           | 11/21/2023  | 1200  |
| 2              | 1           | 11/11/2023  | 200  |
| 3              | 1           |  09/23/2023 |  250 |
| 4              | 1           | 10/23/2023  | 250  |
| 5              | 2           | 03/10/2023  | 1100  |
| 6              | 2           | 11/10/2023  | 1000  |
| 7              | 2           | 07/21/2023  | 90  |
| 8              | 3           | 11/18/2023  | 2200  |
| 9              | 3           |  09/01/2023 | 900  |
| 10             | 3           | 06/21/2023  |  1700    |

## Endpoints
The application has two endpoints, as follows:

1. GET http://localhost:8080/actuator/health

This endpoint is used to check the health of the application. It should return a JSON object with a "status" field and the value "UP" if the application is running:

```json

{
  "status": "UP"
}

```

2. GET http://localhost:8080/getAllRewardPoints

This endpoint is used to get all customer reward points for the last three months. It should return a JSON object containing an array of customer objects, each with their customerId, customerName, and rewardPoint, consisting of the totalRewardPoints and monthlyRewardPoints for the past three months:

```json

[
  {
    "customerId": 1,
    "customerName": "John Sr",
    "rewardPoint": {
      "totalRewardPoint": 3200,
      "monthlyRewardPoint": [
        {
          "monthName": "OCTOBER",
          "monthRewardPoint": 350
        },
        {
          "monthName": "SEPTEMBER",
          "monthRewardPoint": 350
        },
        {
          "monthName": "NOVEMBER",
          "monthRewardPoint": 2500
        }
      ]
    }
  },
  {
    "customerId": 2,
    "customerName": "Tom Jr",
    "rewardPoint": {
      "totalRewardPoint": 1850,
      "monthlyRewardPoint": [
        {
          "monthName": "NOVEMBER",
          "monthRewardPoint": 1850
        }
      ]
    }
  },
  {
    "customerId": 3,
    "customerName": "Mike Jr",
    "rewardPoint": {
      "totalRewardPoint": 5900,
      "monthlyRewardPoint": [
        {
          "monthName": "SEPTEMBER",
          "monthRewardPoint": 1650
        },
        {
          "monthName": "NOVEMBER",
          "monthRewardPoint": 4250
        }
      ]
    }
  }
]

```

## Improvements

One area of improvement for the application would be to update the data population process for the H2 database. Instead of using the current implementation with the

`DataInitializer.java`

file, it would be better to use the sample JSON files created in

`src/main/resources/json/customers.json`

and

`src/main/resources/json/transactions.json`

as the source for the data population.

## Conclusion
With this Customer Reward Point Calculator, customers can easily see their reward point balances and recent earnings. The application can be run locally or on Docker containers, and comes with a test suite to ensure its functionality.
