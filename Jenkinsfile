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
                sh 'mvn clean verify -DskipTests'
            }
        }
        stage('Test') {
            steps {
                echo 'Running Unit Test'
                sh 'mvn test'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Creating a distribution artefact'
                sh 'mvn -Pdist'
            }
        }
    }
}
