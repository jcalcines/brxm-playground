def STAGE_BUILD = true
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

        //TODO: Complete this stage
        stage("SBOM Update") {
            when { expression { return STAGE_DEPENDENCY_CHECK }}
            steps {
                echo 'Generating Software Bill of materials (SBOM) file'
                // Generate the SBOM from maven
                sh 'mvn dependency:tree > report/sbom/sbom.tmp'
                // Checks if there are differences between source control SBOM and currently generated one
                //'sh diff -q report/sbom/sbom.txt report/sbom/sbom.tmp'
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
                // Generates the report from Maven Configuration
                sh 'mvn dependency-check:aggregate'
                // Uses the Jenkins Plugin to display a summary of the report
                dependencyCheckPublisher pattern: 'report/owasp/*/dependency-check-report.xml', failedTotalCritical: 1, unstableNewHigh: 1
            }
            post {
                failure {
                    mail bcc: '', body: 'TODO: Add link to Jenkins Job', cc: '', from: '', replyTo: '', subject: 'Critical vulnerabilities have been found on BloomReach Packages', to: 'jose.calcines@visitscotland.com'
                }
            }
        }
    }
}
