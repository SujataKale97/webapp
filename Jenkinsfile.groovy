node("master"){
  stage ('Build') {
 
    git credentialsId: 'ea4c3770-b2ed-4639-9ffc-cc3e586e454c', url: 'https://github.com/SujataKale97/webapp.git'
    withMaven(
        // Maven installation declared in the Jenkins "Global Tool Configuration"
      maven: 'M3'){
        
    bat 'mvn -Dmaven.test.failure.ignore=true clean package'
    }
  }
  
  stage ('artifact')
  {
      archive 'target/*.war'
  }
  stage ('deploy')
  {
    bat '''copy C:\\Apps\\Jenkins\\jobs\\WebApp-Pipe\\workspace\\target\\*.war C:\\apache-tomcat-7.0.94\\webapps'''
  }
}
