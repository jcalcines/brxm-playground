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
        stage("SBOM Update") {
            when { expression { return STAGE_DEPENDENCY_CHECK }}
            steps {
                // Geerate the SBOM from maven
                //'sh dependency:tree > sbom.log'
                // Checks if there are differences between source control SBOM and currently generated one
                //'sh diff -q sbom.txt sbom.log'
                // If there are differences it replaces sbom.txt with sbom.log
                // TODO
                // Commits the changes to Source Control
                // git commit sbom.txt -m"Software Bill of Materials updated"
                // git push
            }
        }

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
