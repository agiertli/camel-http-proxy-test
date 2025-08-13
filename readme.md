# How to test

```
 - Fix the application.properties based on your preference
 - mvn clean package
 - java -Dquarkus.http.port=8081 -jar target/quarkus-app/quarkus-run.jar        
```

Proxy settings are configured globally via application.properties