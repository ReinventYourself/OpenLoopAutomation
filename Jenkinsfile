pipeline {
    agent any
    
    
   
    stages {
    
    stage('Setup parameters') {
            steps {
                script { 
                    properties([
                        parameters([
                            choice(
                                choices: ['QA', 'Staging'], 
                                name: 'Env'
                            )]
                            )
                            ]
                            )
                            }
    }
    }
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
                bat 'mvn clean install -Dsurefire.suiteXmlFiles=testng.xml'
            }
        }



    }

}