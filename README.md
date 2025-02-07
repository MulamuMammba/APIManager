# MulamuMammba API Manager

## Overview
MulamuMammba API Manager is a Java-based backend application designed to work alongside a React.js frontend. It allows users to test different APIs while simultaneously creating their own RESTful APIs. Simply plug in the base URL, endpoint, and required parameters to see the response.

## Features
- Test various APIs easily by providing the base URL and endpoint.
- Secure authentication and user management.
- API and endpoint management for custom RESTful API creation.
- Security utilities including email validation and password encryption.
- Modular architecture for scalability and maintainability.

## Technologies Used
- **Backend:** Java, Spring Boot
- **Frontend:** React.js (not included in this repository)
- **Database:** SQLITE
- **Security:** Password encryption, email validation

## Directory Structure
```
└── mulamumammba-apimanager/
    ├── mvnw
    ├── mvnw.cmd
    ├── pom.xml
    ├── qodana.yaml
    ├── src/
    │   ├── main/
    │   │   ├── java/
    │   │   │   └── com/
    │   │   │       └── mammba/
    │   │   │           └── APIManager/
    │   │   │               ├── ApiManagerApplication.java
    │   │   │               ├── Config/
    │   │   │               │   └── WebConfig.java
    │   │   │               ├── Controller/
    │   │   │               │   ├── Api/
    │   │   │               │   │   └── ApiController.java
    │   │   │               │   ├── Auth/
    │   │   │               │   │   ├── AuthenticateController.java
    │   │   │               │   │   ├── Authentication.java
    │   │   │               │   │   ├── Authenticator.java
    │   │   │               │   │   ├── LoginController.java
    │   │   │               │   │   └── RegisterController.java
    │   │   │               │   └── EndPoints/
    │   │   │               │       └── EndPointsController.java
    │   │   │               ├── Model/
    │   │   │               │   ├── API.java
    │   │   │               │   ├── Endpoints.java
    │   │   │               │   ├── Response.java
    │   │   │               │   ├── Users.java
    │   │   │               │   └── DTO/
    │   │   │               │       └── LoginRequest.java
    │   │   │               ├── Repository/
    │   │   │               │   ├── ApiTable.java
    │   │   │               │   ├── Database.java
    │   │   │               │   ├── EndpointsTable.java
    │   │   │               │   └── UsersTable.java
    │   │   │               └── Services/
    │   │   │                   ├── ApiTester.java
    │   │   │                   ├── Security/
    │   │   │                   │   ├── EmailValidate.java
    │   │   │                   │   ├── Generator.java
    │   │   │                   │   └── PasswordEncrypt.java
    │   │   │                   └── Usecase/
    │   │   │                       ├── ApiUseCase.java
    │   │   │                       ├── EndPointUseCase.java
    │   │   │                       ├── TesterUseCase.java
    │   │   │                       └── UsersUseCase.java
    │   │   └── resources/
    │   │       └── application.properties
    │   └── test/
    │       └── java/
    │           └── com/
    │               └── mammba/
    │                   └── APIManager/
    │                       └── ApiManagerApplicationTests.java
    └── .mvn/
        └── wrapper/
            └── maven-wrapper.properties
```

## Getting Started
### Prerequisites
- Java 17+
- Maven
- React.js frontend (Optional, but recommended)

### Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/yourusername/mulamumammba-apimanager.git
   ```
2. Navigate to the project directory:
   ```sh
   cd mulamumammba-apimanager
   ```
3. Build the project:
   ```sh
   mvn clean install
   ```
4. Run the application:
   ```sh
   mvn spring-boot:run
   ```
5. The API will be available at `http://localhost:8080`

## Contributing
Contributions are welcome! Please fork the repository and submit a pull request.

## License
This project is licensed under the MIT License.

## Contact
For any issues or suggestions, feel free to open an issue on GitHub.

