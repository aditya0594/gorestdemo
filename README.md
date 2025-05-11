# 🚀 API Test Automation Framework

A modular, maintainable, and scalable API Test Automation Framework built using **Java**, **Cucumber (BDD)**, and **RestAssured**. This project ensures robust validation of APIs through readable feature files, reusable components, and dynamic data handling.

---

## 📁 Project Structure

```
├── features/
│   └── addUser.feature         # Gherkin scenarios for 'Add User' API
├── stepdefinitions/
│   └── StepDefinationFile.java # Step implementations for Gherkin steps
├── utils/
│   └── Utils.java              # Utility methods for dynamic data, request specs, etc.
├── hooks/
│   └── Hooks.java              # Cucumber lifecycle hooks (@Before/@After)
├── config/
│   └── qa.properties           # Environment configuration (base URLs, tokens, etc.)
```

---

## 🧪 Testing Strategy

### ✅ **Approach to Writing Test Flows**
- **BDD with Cucumber:** Human-readable test scenarios aligned with business logic.
- **Modular Step Definitions:** Reusable, clean, and maintainable codebase.
- **Data-Driven Testing:** Random and parameterized data for robust test coverage.
- **API Validation:** Consistent assertions for status codes, fields, and messages.

### 🛠️ **Reliability & Maintainability**
- **Utility Classes:** Encapsulated common logic like request/response handling.
- **Response Specifications:** Unified validations using `ResponseSpecBuilder`.
- **Error Handling:** Assertive and graceful validation of edge cases.
- **Version Control:** GitHub-backed changes and collaboration.

### ⚠️ **Challenges & Solutions**
| Challenge                  | Solution                                                           |
|---------------------------|--------------------------------------------------------------------|
| Dynamic data generation   | Random email/ID generation methods                                 |
| Error response validation | Centralized reusable error validation logic                        |
| Test flakiness            | cleanup strategies                               |
| Scalability               | Logical grouping of scenarios into feature files                   |

---

## 🧪 Sample Feature: `addUser.feature`

```gherkin
@addUser
Scenario: Verify user creation with valid data
  Given I have a valid request payload
  When I send a POST request to "/addUser"
  Then the response status code should be 201
  And the response should contain the user ID and email
```

---

## 🔧 How to Run Tests

### ✅ Pre-requisites
- Java 20
- Maven
- IDE (e.g., IntelliJ IDEA, Eclipse)
- Internet connection (for API access)

### ▶️ Run Command

```bash
mvn test
```

To run specific tags:

```bash
mvn test '-Dcucumber.filter.tags="@addUser"'
```

---

## 🌐 Configuration

Modify `qa.properties` to switch environments:

```properties
baseUrl=https://api.example.com
authToken=Bearer your_token_here
```

---

## 🤝 Contributing

Feel free to fork this repo, raise issues, or open pull requests. We welcome collaboration!

---

## 📜 License

This project is licensed under the [MIT License](LICENSE).

---

## 💡 Key Technologies Used

- **Java 20**
- **Cucumber (BDD)**
- **RestAssured**
- **Maven**
- **JUnit/TestNG**
