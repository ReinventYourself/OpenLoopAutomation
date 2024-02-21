pipeline {
    agent any

    environment {
        // Define environment variables if needed
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout your source code from version control
                checkout scm
            }
        }

        stage('Build') {
            steps {
                // Build your Maven project
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                // Run your tests
                sh 'mvn test -Dsurefire.suiteXmlFiles=testng.xml'
            }
        }

        stage('Deploy') {
            steps {
                // Deploy your application (if applicable)
                // You may need to configure this based on your deployment strategy
            }
        }
    }

    post {
        always {
            // Cleanup or additional tasks that need to run regardless of success or failure
        }
    }
}