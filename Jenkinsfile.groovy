node("master"){
  stage ('Build') {
 
    git credentialsId: 'ea4c3770-b2ed-4639-9ffc-cc3e586e454c', url: 'https://github.com/SujataKale97/webapp.git'
    withMaven(
        // Maven installation declared in the Jenkins "Global Tool Configuration"
      maven: 'M3'){
        
    bat 'mvn clean package'
    }
  }
  
  stage('SonarQube analysis') {
    withSonarQubeEnv('sonar') {
     bat 'mvn clean package sonar:sonar  \
  -Dsonar.projectKey=lu.amazon.aws.demo:WebApp \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=5a419fb91d35fa2abb817fc8f9afd474fedb5a05'
    } // submitted SonarQube taskId is automatically attached to the pipeline context
  }
  stage ('deploy')
  {
    bat '''copy C:\\Apps\\Jenkins\\jobs\\WebApp-Pipe\\workspace\\target\\*.war C:\\apache-tomcat-7.0.94\\webapps'''
  }
}
