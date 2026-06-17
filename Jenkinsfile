pipeline {
    agent any

    environment {
        // Host Windows par ADB server ka access ensure karne ke liye
        ADB_SERVER_SOCKET = 'tcp:localhost:5037' 
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
                echo "Code checked out successfully from GitHub."
            }
        }

        stage('Verify ADB Connection') {
            steps {
                // Ensure adb is in your system PATH on the Jenkins agent
                bat 'adb devices'
            }
        }

        stage('Run Tests via Docker Compose') {
            steps {
                echo "Starting Maven execution block inside Docker container..."
                // Build aur run ek saath
                bat 'docker-compose -f docker-compose1.yml up --build --abort-on-container-exit'
            }
        }
    }

    post {
        always {
            echo "Cleaning up container environments..."
            // Cleanup: remove containers and networks created by compose
            bat 'docker-compose -f docker-compose1.yml down -v'
        }
        success {
            echo "Pipeline Executed Successfully!"
        }
        failure {
            echo "Pipeline Failed. Please inspect execution logs in Jenkins Console."
            // Yahan par tum email notification add kar sakte ho
        }
    }
}