# Extending, Securing and Dockerizing Spring Boot Microservices
"Extending, Securing and Dockerizing Spring Boot Microservices" from LinkedIn Learning.
by, Mary Ellen Bowman, @MEllenBowman



Final Product requires External MySql Database.
Install Docker For Mac/Windows/Linux
#### Setup
Set JAVA_HOME
Set M2_HOME
Add M2_HOME/bin to the execution path
mvn package -DskipTests
#### Docker Commands
##### Start MySql Container (downloads image if not found)
``
docker run  --detach   --name ec-mysql -p 6604:3306 -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=explorecali -e MYSQL_USER=cali_user -e MYSQL_PASSWORD=cali_pass -d mysql
``

##### view all images
``
docker images
``

##### view all containers (running or not)
``
docker ps -a
``
##### Interact with Database (link to ec-mysql container) with mysql client
``
docker run -it --link ec-mysql:mysql --rm mysql sh -c 'exec mysql -h"$MYSQL_PORT_3306_TCP_ADDR" -P"$MYSQL_PORT_3306_TCP_PORT" -uroot -p"$MYSQL_ENV_MYSQL_ROOT_PASSWORD"'
``
##### Stop ec-mysql container
``
docker stop ec-mysql
``
##### (ReStart) ec-mysql container
``
docker start ec-mysql
``
##### Remove ec-mysql container (must stop it first)
``
docker rm ec-mysql
``
##### Remove image (must stop and remove container first)
``
docker rmi mysql:latest
``
#### Startup with Profile settings
##### Default profile, H2 database
``
mvn spring-boot:run
``

or

``
java  -jar target/explorecali-2.0.0-SNAPSHOT.jar
``
##### mysql profile, MySql database (requires running container ec-mysql)
``
mvn spring-boot:run -Dspring.profiles.active=mysql 
``

or

``
java  -Dspring.profiles.active=mysql -jar target/explorecali-2.0.0-SNAPSHOT.jar
``
#### Dockerize Explore California
##### Build jar, image, set default profile
``
mvn package -DskipTests docker:build 
``
###### container with default property set in Dockerfile
``
docker run --name ec-app-default -p 8080:8080  -d explorecali-default
``
##### Build jar, image, set mysql profile
``
mvn package -DskipTests docker:build -Dec-profile=mysql
``
##### Run Docker container with mysql profile
``
docker run    --name ec-app-mysql -p 8181:8080  --link ec-mysql:mysql -d explorecali-mysql
``
##### Build jar, image, set docker profile
``
mvn package -DskipTests docker:build -Dec-profile=docker
``
##### Run Docker container with docker profile set in Dockerfile and migration scripts on host
``
docker run --name ec-app-docker -p 8282:8080 -v ~/db/migration:/var/migration -e server=ec-mysql -e port=3306 -e dbuser=cali_user -e dbpassword=cali_pass --link ec-mysql:mysql -d explorecali-docker
``
##### Enter Docker container
``
docker exec -t -i ec-app /bin/bash
``
#####