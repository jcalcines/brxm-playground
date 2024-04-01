def CREATE_DISTRIBUTION = false

pipeline {
    agent any

    environment {
        MAVEN_SETTINGS = credentials('maven-settings')
    }

    tools {
        maven 'Maven 3.5.4'
        jdk 'jdk11'
    }

    stages {
        stage('Build') {
            steps {
                echo 'Bulding the project'
                mvn clean verify -DskipTests
            }git
        }
        stage('Test') {
            steps {
                echo 'Running Unit Test'
                mvn test
            }
        }
        stage('Deploy') {
            steps {
                echo 'Creating a distribution artefact'
                mvn -P dist
            }
        }
    }
}
