pipeline {
    agent any

    environment {
        // Points the in-container Appium/ADB commands to your windows host machine
        ADB_SERVER_SOCKET = 'tcp:localhost:5037' 
    }

    stages {
        stage('Checkout Code') {
            steps {
                // Pulls latest script configurations from your github repo
                checkout scm
                echo "Code checked out successfully from GitHub."
            }
        }

        stage('Verify ADB Connection') {
            steps {
                // Verifies if the host ADB server can view your attached hardware
                bat 'adb devices'
            }
        }

        stage('Run Tests via Docker Compose') {
            steps {
                echo "Starting Maven execution block inside Docker container..."
                // Launches the docker compose environment, runs test suites, and tears down container post execution
                bat 'docker-compose -f docker-compose1.yml up --build --abort-on-container-exit'
            }
        }
    }

    post {
        always {
            echo "Cleaning up container environments..."
            bat 'docker-compose -f docker-compose1.yml down'
        }
        success {
            echo "Pipeline Executed Successfully! Notification mail triggered."
            // validation email step goes here
        }
        failure {
            echo "Pipeline Failed. Please inspect execution logs."
        }
    }
}