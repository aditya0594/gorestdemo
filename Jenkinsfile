pipeline {
    agent any

    environment {
        ENV = 'qa'
    }

    tools {
        jdk 'jdk-20' // ✅ Must match what's configured in Jenkins
        maven 'apache-maven-3.9.6-bin' // ✅ Exact Maven name
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

        stage('Archive Test Reports') {   // ✅ Proper placement
            steps {
