def STAGE_BUILD = false
def STAGE_DEPENDENCY_CHECK = true

pipeline {
    agent any

    // https://www.jenkins.io/doc/book/pipeline/docker/
//        { docker { image 'docker maven:3.5.4-jdk-11'}}

    /**
     * The tools defined in this section need to be configured in Jenkins under
     * Dashboard > Manage Jenkins > Tools
     * https://http://jenkins.local:8080/manage/configureTools/
     */
    tools {
        maven 'Maven 3.9.6'
        jdk 'jdk 11'
        dependency-check 'Dependency Check 9.1.0'
    }

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
        stage ()

        stage("OWASP Dependency Check Publisher") {
            // https://www.jenkins.io/doc/pipeline/steps/dependency-check-jenkins-plugin/
            when { expression { return STAGE_DEPENDENCY_CHECK }}
            steps {
                sh 'mvn dependency-check:aggregate'

                dependencyCheckPublisher pattern:dependency-check-report.xml
            }
        }
    }
}
