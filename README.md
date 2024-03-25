# Beer Tap Dispenser API Documentation

## Introduction
The Beer Tap Dispenser API provides endpoints to manage beer dispenser units and retrieve expenses related to beer consumption.

## Base URL
The base URL for accessing the API is `/api/v1/dispenser`.

## Endpoints

### 1. Create Dispenser
- **Method:** POST
- **URL:** `/`
- **Description:** Creates a new beer dispenser unit.
- **Request Body:** JSON object representing the dispenser.
  - `flow_volume` (integer): The volume of beer flow for the dispenser.
- **Response:** Returns a response containing the created dispenser's flow volume and ID.
  ```json
  {
    "flow_volume": 500,
    "id": "38c1a684-1266-4a3b-9014-7509773fd1a3"
  }

 ### 2. Change Dispenser Status
- **Method:** PUT
- **URL:** /{id}/status
- **Description:** Changes the status of a dispenser.
- **Path Parameters:**
- **id (UUID):** The unique identifier of the dispenser.
- **Request Body:** JSON object containing the status change details.
- **status (string):** The new status of the dispenser.
- **updatedAt (string):** The timestamp indicating when the status was updated.
- **Response:** Returns a response indicating the success or failure of the status change.
Returns HTTP status code 200 if successful.
Returns HTTP status code 500 with an error message if an unexpected error occurs.

### 3. Get Dispenser Spending
- **Method:** GET
- **URL:** /{id}/spending
- **Description:** Retrieves the expenses related to beer consumption for a dispenser.
- **Path Parameters:**
- **id (UUID):** The unique identifier of the dispenser.
- **Response:** Returns a response containing the dispenser's spending details.
- {
  "totalSpent": 250.5,
  "history": [
    {
      "date": "2024-03-25T12:00:00Z",
      "volume": 100,
      "price": 50
    },
    {
      "date": "2024-03-24T12:00:00Z",
      "volume": 200,
      "price": 100.5
    }
  ]
}

## Error Handling
The API returns appropriate HTTP status codes along with error messages for any encountered errors.
Error responses include detailed error messages to assist in debugging.

## Authentication
Authentication is required for accessing certain endpoints of the API. Implement authentication mechanisms as per your application's requirements.

## Architecture Considerations
### Microservices Architecture
In a microservices architecture, it's beneficial to separate concerns to achieve modularity, scalability, and maintainability. One key aspect of this separation is dividing functionalities into independent services. Here's why having the authentication service separate is advantageous:

Modularity: By separating the authentication service from the dispenser management service, each service can focus on its specific functionality without being tightly coupled to other parts of the system. This improves maintainability as changes in one service are less likely to impact others.

Scalability: Different services may have different scalability requirements. For example, the authentication service might experience heavier loads during peak login times, while the dispenser management service might have more stable traffic patterns. Separating these services allows you to scale them independently to handle their respective workloads efficiently.

Security: Centralizing authentication logic in a dedicated service helps enforce consistent security policies across multiple APIs and applications. This reduces the risk of security vulnerabilities caused by inconsistent implementation of authentication mechanisms.

Technology Flexibility: Separating authentication into its own service allows you to choose the most suitable technologies and frameworks specifically tailored for authentication requirements. This flexibility enables you to adopt new authentication methods or technologies without affecting other parts of the system.

By keeping the authentication service separate from the dispenser management service, you can maintain a clean separation of concerns and achieve the benefits of a microservices architecture, including modularity, scalability, and security.

## Testing
Comprehensive testing ensures the reliability and robustness of the Beer Tap Dispenser API:
All tests have passed successfully, confirming the correctness and reliability of the Beer Tap Dispenser API. Continuous integration and automated testing pipelines are in place to ensure that tests are executed regularly and any regressions are detected early in the development cycle.
