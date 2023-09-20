FROM openjdk
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

#./gradlew jar
#docker build --build-arg JAR_FILE="build/libs/ArchibaldServer-0.0.1-SNAPSHOT.jar" -t krutoypan3/archibald_server .

#docker rm archibald_spring --force
#docker run -v archibald_volume:/archibald_server -d --name archibald_spring -p 80:80 krutoypan3/archibald_server