pipeline {
    agent any

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

    }

}