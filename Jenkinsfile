pipeline {
    agent any

    tools {
        // Pakka kar lena ki aapke Jenkins Global Tool Configuration mein Maven ka naam 'Maven3' ya jo bhi ho, wahi yahan likha ho
        maven 'Maven3'
        jdk 'Java17'
    }

    environment {
        // Aapke execution ki bypass command
        TEST_COMMAND = "mvn clean test -Dtest=MobileAutomationPipelineTest"
    }

    stages {
        stage('Checkout Code') {
            steps {
                // Yeh stage GitHub se aapka fresh code pull karega
                checkout scm
                echo "Bhai, GitHub se fresh code pull ho gaya hai!"
            }
        }

        stage('Verify Environment') {
            steps {
                // Java aur Maven ka version check karna safetly ke liye
                bat 'java -version'
                bat 'mvn -version'
            }
        }

        stage('Run Mobile Automation Tests') {
            steps {
                echo "🚀 Appium Automation Test Flow Shuru Ho Raha Hai..."
                // Windows machine ke liye 'bat' command use hoti hai
                bat "${TEST_COMMAND}"
            }
        }
    }

    after {
        always {
            echo "--- Pipeline Execution Poora Hua ---"
            // TestNG ke reports ko archive karna taaki Jenkins dashboard par dikhe
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