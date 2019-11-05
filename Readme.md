Features:

    - integration with SpringBootAngularDemo
    - rest controller with camel
    - postman folder
    - swagger documentation
    
Pending:

    - swagger try out "TypeError: Failed to fetch"    

Para conectarse a la mensajeria, tener primero levantado activemq (apache-activemq-5.8.0):

    cd apache-activemq-5.8.0/bin/win32 
    activemq

Para ejecutar el proyecto en forma local basta con ejecutar el siguiente comando:

    mvn spring-boot:run
    
The service exposes a swagger api documentation you can access at:
    
    http://localhost:[server.port]/camel/api-doc
    
Can run Swagger UI using Docker by running the following command line:

    docker run -d --name swagger-ui -p 8080:8080 swaggerapi/swagger-ui    
    
You can get a list of all active and inactive containers by passing the -a flag to the docker container ls command:
    
    docker container ls -a
    
Once you know the CONTAINER ID of the containers you want to delete, pass it to the docker container rm command. 

    docker container rm [CONTAINER ID]
    
Open a web browser: 

    http://localhost:8080/ 

Then type the following URL in the URL field and click the Explore button:   

    http://localhost:[server.port]/camel/api-doc
