pipeline {
    agent any

    environment {
        ENV = 'qa' // Set your environment here
    }

    triggers {
        // Optional: Polling or GitHub webhooks can be configured in Jenkins UI
    }

    tools {
        jdk 'jdk20' // You must configure JDK 20 in Jenkins Global Tools
        maven 'Maven3' // You must configure Maven in Jenkins Global Tools
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Build and Run Tests') {
            steps {
                echo "Running tests in environment: ${ENV}"
                sh "mvn test -Denv=${ENV}"
            }
        }

        stage('Archive Test Reports') {
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                    archiveArtifacts artifacts: 'target/surefire-reports/**', fingerprint: true
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished.'
        }
    }
}
