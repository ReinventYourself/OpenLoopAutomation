pipeline {
    agent any
    
environment {
    MAVEN_HOME = 'C:\\apache-maven-3.9.6-bin\\apache-maven-3.9.6'
    PATH = "\"$MAVEN_HOME\\bin\";$PATH"
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
                bat 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                // Run your tests
                //env.MAVEN_OPTS = '-DforkCount=0'
                bat mvn test -Dsurefire.suiteXmlFiles=testng.xml'
            }
        }

    }

}