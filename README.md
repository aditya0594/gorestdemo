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

NOTE : If token expries, you have to generate from the gorest site
```

----------------------------------------------------------------------------------------------------

🏗️ Jenkinsfile Integration
Jenkinsfile Overview
The Jenkinsfile is designed to automate the execution of the tests in this API Test Automation Framework. It defines the stages and steps involved in the CI/CD pipeline for building, testing, and deploying the project.

How It Works
Source Code Checkout:

The Jenkins pipeline begins by pulling the latest code from the configured Git repository.

Build Stage:

Maven Build: The Jenkins pipeline executes mvn clean install to ensure that the project is compiled and the necessary dependencies are downloaded.

Test Stage:

Cucumber Test Execution: In this stage, Jenkins executes the API tests using Maven. The Cucumber feature files and corresponding step definitions are used to run the tests.

You can run the tests with the default configuration, or specify specific tags to run a subset of tests, such as @addUser.

Post-Build Actions:

Test Results Archiving: The results of the test execution are collected and stored for review.

Notifications: Depending on the build's outcome, Jenkins can notify team members (via email, Slack, etc.) about the success or failure of the build.

Artifact Archiving: If configured, Jenkins archives test reports and other artifacts generated during the build.

Example Jenkinsfile
Here's an example of how the Jenkinsfile looks for this project:

groovy
Copy
Edit
pipeline {
agent any

    environment {
        BASE_URL = 'https://api.example.com'
        AUTH_TOKEN = 'Bearer your_token_here'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/your-repo/project.git'
            }
        }
        
        stage('Build') {
            steps {
                script {
                    sh 'mvn clean install'
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    sh 'mvn test -Dcucumber.filter.tags="@addUser"'
                }
            }
        }
        
        stage('Post-Build') {
            steps {
                archiveArtifacts allowEmptyArchive: true, artifacts: '**/target/*.html', onlyIfSuccessful: true
                junit '**/target/test-*.xml'
            }
        }
    }

    post {
        success {
            echo 'Build and tests executed successfully!'
        }
        failure {
            echo 'Build failed, please check the logs!'
        }
    }
}
Breakdown of Jenkinsfile:
Checkout: This stage checks out the latest code from the GitHub repository.

Build: This stage executes mvn clean install to build the project and download the required dependencies.

Test: This stage runs the Cucumber tests using Maven and executes tests based on specific tags (e.g., @addUser).

Post-Build: This stage archives the test results and generates a test report. It also stores the test results in Jenkins for further analysis.
---------------------------------------------------------------------------------------------------------------------------------------------------
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
- **TestNG**
