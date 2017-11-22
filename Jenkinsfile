pipeline {
  agent any
  stages {
    stage('Checkout') {
      steps {
        sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
      }
    }
    stage('Build') {
      parallel {
        stage('Build') {
          steps {
            sh 'echo \'hello\''
          }
          post {
            success {
              echo 'junit target/surefire-reports/**/*.xml'
              
            }
            
          }
        }
        stage('Dependency Resolving') {
          steps {
            sh 'echo \'resolving\''
          }
        }
      }
    }
    stage('Test') {
      parallel {
        stage('Test') {
          steps {
            sleep 3
          }
        }
        stage('Unit Test') {
          steps {
            sh 'echo \'junit\''
          }
        }
        stage('UI Test') {
          steps {
            sh 'echo \'selenium\''
          }
        }
        stage('STC Check') {
          steps {
            sh 'echo \'stc\''
          }
        }
      }
    }
    stage('Artifact Upload') {
      steps {
        sh 'echo \'upload\''
      }
    }
  }
}