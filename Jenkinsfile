pipeline {
    agent any
    
       environment {
        DOCKER_HUB_USERNAME = "gauravs2089"
        DOCKER_HUB_PASSWORD = "Qazwsxed123!")
        DOCKER_IMAGE_NAME = "gauravs2089/restassuredtest"
        BUILD_VERSION = "${env.BUILD_NUMBER}"
    }
    
   
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
        
                stage('Build and Publish Docker Image') {
            steps {
            
             script {
                    
      // Build the Docker image with the Jenkins build number as the tag
                    bat "docker build -t ${DOCKER_IMAGE_NAME}:${BUILD_VERSION} ."

                    // Log in to Docker Hub
                    bat "docker login -u ${DOCKER_HUB_USERNAME} -p ${DOCKER_HUB_PASSWORD}"

                    // Push the Docker image to Docker Hub with the Jenkins build number as the tag
                    bat "docker push ${DOCKER_IMAGE_NAME}:${BUILD_VERSION}"
                    
                }
            }
        }



    }

}