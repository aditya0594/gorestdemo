pipeline {
    agent any

    environment {
        ENV = 'qa' // Set your environment here
    }

    tools {
        jdk 'jdk20' // You must configure JDK 20 in Jenkins Global Tools
        maven 'apache-maven-3.9.6-bin' // You must configure Maven in Jenkins Global Tools
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
