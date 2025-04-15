## Unit test standards 1: AAA & FIRST
This project implements how to use Junit with Java to do the testing using AAA &FIRST standards for implementation.


## Requirements
1. Java
2. Junit


### How To Run

1. Clone this repository.

2. Checkout to `week05_5.1P` branch.

3. Open eclips and import the project (as a maven project).

4. Run the `Main.java` file.

5. Run the `WeatherController.java` file.

6. Run the `WeatherControllerTest.java` file.

### Use cases where unit testing standards are difficult to follow and possible workarounds

| Challenge                        | Workaround                                                  | Impact                                                      |
| -------------------------------- | ----------------------------------------------------------- | ----------------------------------------------------------- |
| Code organization                | Separate test and production code; mirror structure         | Risk of shipping test code                                  |
| Complex Arrange/Act/Assert flow  | Use of @Before, utility methods, and keep tests focused     | Hard to read and maintain tests                             |
| Multiple dependencies            | Use mocking, stubs, and DI                                  | Setup becomes bloated                                       |
| Poor test naming                 | Apply descriptive names, constants, and Hamcrest assertions | Low readability and maintainability                         |
| Testing private data             | Fragile and insecure tests                                  | Refactor logic to public-facing units or use helper classes |
