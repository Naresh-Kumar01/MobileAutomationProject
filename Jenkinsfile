pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'Java17'
    }

    environment {
        // Yahan bypass command daal dijiye jo direct test class ko run karegi bina XML ke
        TEST_COMMAND = "mvn test -Dtest=MobileAutomationPipelineTest"
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
                bat "${TEST_COMMAND}"
            }
        }
    }

    after {
        always {
            echo "--- Pipeline Execution Poora Hua ---"
            // Jab test bina XML ke chalega toh junit reports archive karne ke liye is line ko rehte dete hain
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