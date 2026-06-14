pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'Java17'
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
                echo "Bhai, GitHub se fresh code pull ho gaya hai!"
            }
        }

        stage('Verify Environment') {
            steps {
                bat 'java -version'
                bat 'mvn -version'
            }
        }

        stage('Run Mobile Automation Tests') {
            steps {
                echo "🚀 Appium Automation Test Flow Shuru Ho Raha Hai..."
                // Yahan direct command hardcode kar di hai bina kisi environment variable ke
                bat 'mvn test -Dtest=MobileAutomationPipelineTest'
            }
        }
    }

    after {
        always {
            echo "--- Pipeline Execution Poora Hua ---"
            junit '**/target/surefire-reports/*.xml'
        }
        success {
            echo "🎉 Badhai ho bhai! Jenkins Pipeline ekdum SUCCESS rahi!"
        }
        failure {
            echo "❌ Arre yaar, Build Fail ho gayi. Logs check karo!"
        }
    }
}