# microservices-reference-code
This repository contains code references to sample microservices application "ReviveStyle"

## Folder Structure  
> **_NOTE:_**  All the instructions related to sub-folders are mentioned as part of the respective README file 

- ## chapter4-catalog
  - Contains the microservices code related to Catalog domain 
- ## chapter7-cart
  - Contains the microservices code related to Cart domain.This includes Cart & Inventory Microservices 
- ## chapter8-order
  - Contains the microserivces code related to Order domain .This includes Order microservices (payment is embedded as part of Order itself) 
- ## api-collection
  - Contains All the Bruno & Postman collections  that are required to test respective microservice .Make sure if any changes made to the application context-path,port,and host-nane needs to be updated in the respective collection variable

## Instructions - How to Setup 
- All the projects are quarkus based java projects and tested on OpenJDK-21
- Make sure JAVA_HOME is set against the right JDK
- Download MAVEN and set MAVEN_HOME / M2_HOME in order to build the project
- Make sure each project respective maven build definition pom.xml is modified according to your database settings
- If you're going to run all the services together make sure the port conflict didn't occur
  - > **_NOTE:_** change the port of respective microservice in its application.properties (use * *quarkus.http.port=<YOUR_PREFERRED_PORT>* *)
- Create the empty database in respective DB store
- Run the database migration (using maven command - **mvn flyway:migrate**) as this step would ensure the basic tables and seed data are created as part of database
- Run the application using command - **mvn quarkus:dev** ) to bring the services running
- To test against thee services we have given Bruno & Postman  collections (select your choice of tool)
  -  configure the respective collection / environment variables that is align with your previous settings (services port , context path changes (if any made) )
-  This should bring the application Running !!!
-  Happy Learning and Weaving Microservices !!!

  > **_NOTE:_**  We are happy to understand from you all how it goes and feel free to reach out as it would help us improvise !!!
