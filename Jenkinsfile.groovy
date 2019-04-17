node("master"){
  stage ('Build') {
 
    git credentialsId: 'ea4c3770-b2ed-4639-9ffc-cc3e586e454c', url: 'https://github.com/SujataKale97/simple-java-maven-app.git'
    withMaven(
        // Maven installation declared in the Jenkins "Global Tool Configuration"
      maven: 'M3'){
        
     bat label: '', script: '''javac HelloWorld.java
java HelloWorld'''
    }
  }
}
