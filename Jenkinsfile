pipeline {
    agent any
    
    parameters {
        string(name: 'Env', defaultValue: 'QA', description: 'Environment to execute')
    }
    
    stages {
        stage('Checkout') {
            steps {
                   checkout scm
            }
        }

        stage('Build') {
            steps {
             script {
                    
                    // def prop = readProperties file: 'src/main/resources/config.properties'

                    def propertiesFile = ${WORKSPACE}+'/src/main/resources/config.properties'
                    echo propertiesFile
                    def properties = new Properties()
                    properties.load(new FileInputStream(propertiesFile))

                    // Update the environment property
                         properties.put('Env', params.Env)

                    // Write the updated properties back to the file
                    //writeProperties file: 'src/main/resources/config.properties', properties: properties
                    properties.store(new FileWriter(propertiesFile), null) 
                    
                    
                }
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