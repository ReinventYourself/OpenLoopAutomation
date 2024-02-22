pipeline {
    agent any
    
   parameters {
    string(name: 'Env', defaultValue: 'QA,Staging', description: 'Environment to execute', allowMultiple: true)
}
    
    
    stages {
        stage('Checkout') {
            steps {
                   checkout scm
            }
        }

        stage('Build and Test') {
            steps {
            
             script {
                    
                    workspaceDir = "${WORKSPACE}"
                    echo "this is work ${workspaceDir}"
                    def propertiesFile = "${workspaceDir}/src/main/resources/config.properties"
                  //  echo propertiesFile
                    def properties = new Properties()
                    properties.load(new FileInputStream(propertiesFile))
                    properties.put('Env', params.Env)
                    properties.store(new FileWriter(propertiesFile), null) 
                    
                }
                // Build your Maven project
                bat 'mvn clean install -Dsurefire.suiteXmlFiles=testng.xml'
            }
        }



    }

}