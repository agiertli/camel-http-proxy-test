# How to test

```
 - Fix the application.properties based on your preference
 - mvn clean package
 - java -Dhttp.proxyHost=localhost -Dhttp.proxyPort=8000 -Dquarkus.http.port=8081 -jar target/quarkus-app/quarkus-run.jar        
```