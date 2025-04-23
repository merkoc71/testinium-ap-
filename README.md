# Trello API Automation

This project demonstrates the automation of Trello's REST API using Java, Maven, and REST-assured.

## Project Overview

The automation follows these steps:
1. Create a new Trello board
2. Create two cards on the board
3. Update one of the cards randomly
4. Delete all created cards
5. Delete the board

## Technologies Used

- Java 11
- Maven
- REST-assured for API testing
- TestNG as test framework
- Jackson for JSON parsing
- Lombok for reducing boilerplate code
- Log4j2 for logging

## Project Structure

The project follows the Page Object Pattern to maintain a clean separation of concerns:

- `client` package: Contains base and specific API clients for Trello resources
- `config` package: Contains configuration handling
- `models` package: Contains data models for Trello resources
- `services` package: Contains service classes implementing the Page Object Pattern
- `utils` package: Contains utility classes for the project
- `tests` package: Contains test classes

## Setup Instructions

1. Clone the repository
2. Get your Trello API Key and Token:
   - Visit https://trello.com/app-key (you must be logged in to Trello)
   - Generate your API Key and Token
3. Update the `src/main/resources/config.properties` file with your API credentials:
   ```
   trello.api.key=YOUR_TRELLO_API_KEY
   trello.api.token=YOUR_TRELLO_API_TOKEN
   ```
4. Run the tests using Maven:
   ```
   mvn clean test
   ```

## Notes

- This project implements Object-Oriented Programming (OOP) principles
- It uses the Page Object Pattern for maintainability
- REST-assured is used for API interactions
- The test automatically cleans up after itself by deleting created resources

## Contact

For questions or issues, please contact:
- batuhan.zafer@testinium.com
- tam@testinium.com 