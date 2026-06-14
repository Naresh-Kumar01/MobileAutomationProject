pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK21' 
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo "🚀 GitHub se fresh code automatic pull ho raha hai..."
                checkout scm
            }
        }

        stage('Spin Up Docker Grid') {
            steps {
                echo "🐳 Existing system conflicts ko stop aur remove kar rahe hain..."
                bat "docker rm -f selenium-hub || true"
                bat "docker-compose down -v --remove-orphans"
                
                echo "🚀 Fresh Docker Containers up kar rahe hain..."
                bat "docker-compose up -d"
                
                echo "⏳ 10 Seconds rukte hain taaki Grid fully ready ho jaye..."
                sleep time: 10, unit: 'SECONDS'
            }
        }

        stage('Run Mobile Automation Tests') {
            steps {
                echo "🚀 Appium Automation Test Flow Shuru Ho Raha Hai..."
                bat "mvn test -Dtest=MobileAutomationPipelineTest"
            }
        }
    }

    post {
        always {
            echo "🧹 Cleaning up Docker containers..."
            bat "docker-compose down -v"
            echo "--- Pipeline Execution Poora Hua ---"
        }
    }
}