pipeline {
    agent any
    tools {
        maven 'M3'
        jdk 'LocalJDK'
    }
    stages {
        
        stage ('Build') {
            steps {
                sh 'mvn -Dmaven.test.failure.ignore=true install' 
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml' 
                }
            }
        }
    }
}
