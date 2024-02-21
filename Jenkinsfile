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
                bat 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                // Run your tests
                //env.MAVEN_OPTS = '-DforkCount=0'
                bat 'mvn test -Dsurefire.suiteXmlFiles=testng.xml'
            }
        }

    }

}