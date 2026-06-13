pipeline {
    agent any

    tools {
        // Ensure aapke Jenkins mein Maven 'M3' ya jo bhi naam aapne Global Tool Configuration mein diya hai, woh mapped ho
        maven 'M3'
    }

    stages {
        stage('🚀 Checkout Code') {
            steps {
                echo 'Pulling latest automation code from Git...'
                checkout scm
            }
        }

        stage('⚡ Check & Start Appium Server') {
            steps {
                script {
                    echo 'Checking if Appium Server is already running on port 4723...'
                    // Windows par check karega ki port 4723 busy hai ya nahi, nahi hai toh Appium background mein start karega
                    bat """
                        netstat -ano | findstr 4723 > nul
                        if %errorlevel% neq 0 (
                            echo Appium is down. Starting Appium Server in background...
                            start /B appium
                            timeout /t 5 > nul
                        ) else (
                            echo Appium Server is already up and running!
                        )
                    """
                }
            }
        }

        stage('📲 Run Mobile Automation Tests') {
            steps {
                echo 'Executing Maven Appium Automation Test Suite...'
                // Humne jo command local par chalayi thi, wahi Jenkins execute karega
                bat 'mvn clean test'
            }
        }
    }

    post {
        always {
            echo 'Archiving TestNG Results...'
            // Surefire reports ko Jenkins dashboard par dekhne ke liye publish karega
            junit '**/target/surefire-reports/*.xml'
        }
        success {
            echo '🏆 Badhaai ho bhai! Jenkins Pipeline Successful! Mail trigger sending...'
        }
        failure {
            echo '❌ Automation Build Failed! Checking logs...'
        }
    }
}