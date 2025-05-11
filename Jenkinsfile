pipeline {
    agent any

    environment {
        ENV = 'qa' // You can change this to dev, prod, etc.
    }

    tools {
        jdk 'jdk-20' // Make sure JDK 20 is configured in Jenkins as 'jdk-20'
        maven 'apache-maven-3.9.6-bin' // Make sure Maven is configured with this exact name
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Build and Test') {
            steps {
                echo "Running tests in environment: ${ENV}"
                sh "mvn test -Denv=${ENV}"
            }
        }

        stage('Archive Test Reports') {
            steps {
                junit 'target/surefire-reports/*.xml'
                archiveArtifacts artifacts: 'target/surefire-reports/**', fingerprint: true
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished.'
        }
    }
}
