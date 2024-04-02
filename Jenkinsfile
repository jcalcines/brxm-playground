def STAGE_BUILD = false
def STAGE_MAVEN_DEPENDENCY_CHECK = false
def STAGE_DEPENDENCY_CHECK = true

pipeline {
    agent {
        docker { image 'docker maven:3.5.4-jdk-11'}
    }

//    environment {
//        MAVEN_SETTINGS = credentials('maven-settings')
//    }



//    tools {
//        maven 'Maven 3.5.4'
//        jdk 'jdk11'
//    }

    stages {
        stage('Build') {
            when { expression { return STAGE_BUILD }}
            steps {
                echo 'Bulding the project'
                sh 'mvn clean verify -DskipTests'
            }
        }
        stage('Test') {
            when { expression { return STAGE_BUILD }}
            steps {
                echo 'Running Unit Test'
                sh 'mvn test'
            }
        }

        stage('Deploy') {
            when { expression { return STAGE_BUILD }}
            steps {
                echo 'Creating a distribution artefact'
                sh 'mvn -Pdist'
            }
        }

        stage('Vulnerability Scan Maven') {
            when { expression { return STAGE_MAVEN_DC }}
            steps {
                sh 'mvn dependency-check:aggregate'
            }
        }

        stage("Dependency check Publisher") {
            // https://www.jenkins.io/doc/pipeline/steps/dependency-check-jenkins-plugin/
            when { expression { return STAGE_DEPENDENCY_CHECK }}
            steps {
                dependencyCheck skipOnScmChange: true, skipOnUpstreamChange: true

//                dependencyCheckPublisher
            }
        }
    }

    post {

        always {
            echo 'Summary: '
        }
        success {
            echo '  The pipeline has ran successfully'
        }
        failure {
            echo '  The pipeline has failed to run'
        }
        unstable {
            echo '  This build has been marked as unstable'
        }
        fixed {
            echo 'The pipeline is now stable'
        }

    }
}
