BloomReach Playground Repository
==============================

This project acts a playground for testing features in an isolated environment.

The idea is to create branches with different integrations and tools so we can not only see features in isolation but to 
understand the differences with the original code


Running Locally
===============

This project uses the Maven Cargo plugin to run Essentials, the CMS and site locally in Tomcat.
From the project root folder, execute:

    mvn clean verify
    mvn -P cargo.run

Access the Bloomreach setup application at <http://localhost:8080/essentials>.
After your project is set up, access the CMS at <http://localhost:8080/cms> and the site at <http://localhost:8080/playground>.
Logs are located in target/tomcat9x/logs


