pipeline {
    agent any
    
       environment {
        DOCKER_HUB_USERNAME = "gauravs2089"
        DOCKER_HUB_PASSWORD = "Qazwsxed123!"
        DOCKER_IMAGE_NAME = "gauravs2089/restassuredtest"
        BUILD_VERSION = "${env.BUILD_NUMBER}"
        JOB_Name ="${env.JOB_NAME}"
        def XmlFile='testng.xml'
    }
    
   
    stages {
    
    stage('Setup parameters') {
            steps {
                script { 
                    properties([
                        parameters([
                            choice(
                                choices: ['QA', 'Staging'], 
                                description: 'Please choose the Environment where you want to execute the test',
                                name: 'Env'
                            ),
                             choice(
                                choices: ['False', 'True'],
                                description: 'Select True if you want to send report to the configured recipient',
                                name: 'EmailSend'
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
                    //echo "this is work ${workspaceDir}"
                    def propertiesFile = "${workspaceDir}/src/main/resources/config.properties"
                  //  echo propertiesFile
                    def properties = new Properties()
                    properties.load(new FileInputStream(propertiesFile))
                    properties.put('Env', params.Env)
                    properties.put('EmailSend', params.EmailSend)
                    properties.store(new FileWriter(propertiesFile), null) 
                    //echo XmlFile
                    //echo "job name ${JOB_Name}"
                  if (JOB_Name.contains('Smoke')) {
                      XmlFile = 'testngSmoke.xml'
                      //echo "update file ${XmlFile}"
                        } 
                                  
                       //echo XmlFile 
                    
                }
                
                bat "mvn clean install -Dsurefire.suiteXmlFiles=${XmlFile}"
            }
        }
        
          stage('Build and Publish Docker Image') {
            steps {
            
             script {
            if (!JOB_Name.contains('Smoke')) {
             
             def sanitizedEnvironment = params.Env.replaceAll("[^a-z0-9]", "-").toLowerCase()
                    echo sanitizedEnvironment
      // Build the Docker image with the Jenkins build number as the tag
                    bat "docker build -t ${DOCKER_IMAGE_NAME}:${BUILD_VERSION}:${sanitizedEnvironment} ."

                    // Log in to Docker Hub
                    bat "docker login -u ${DOCKER_HUB_USERNAME} -p ${DOCKER_HUB_PASSWORD}"

                    // Push the Docker image to Docker Hub with the Jenkins build number as the tag
                    bat "docker push ${DOCKER_IMAGE_NAME}:${BUILD_VERSION}:${sanitizedEnvironment}"
                    
                }
            }
        }
        }
    }

}