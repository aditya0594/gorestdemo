pipeline {
    agent any

    environment {
        ENV = 'qa'
    }

    tools {
        jdk 'jdk-20'
        maven 'apache-maven-3.9.6-bin'
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Build and Test') {
            steps {
                script {
                    if (isUnix()) {
                        sh "mvn test -Denv=${ENV}"
                    } else {
                        bat "mvn test -Denv=${ENV}"
                    }
                }
            }
        }

        stage('Archive Test Reports') {
            steps {
                script {
                    def reportPath = isUnix() ? 'target/surefire-reports/*.xml' : 'target\\surefire-reports\\*.xml'
                    junit reportPath

                    def artifactPath = isUnix() ? 'target/surefire-reports/**' : 'target\\surefire-reports\\**'
                    archiveArtifacts artifacts: artifactPath, fingerprint: true
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
