# How to test

 - Fix the application.properties based on your preference
 - mvn clean package
 - java -jar target/quarkus-app/quarkus-run.jar        

 Key aspects:

  - camel-http proxy configuratino is applied *once* via ProxyConfiguration.java
  - Actual proxy host and proxy port values are *defined* in application.properties (no need to pass them via java -jar -D..)
  - camel-http component is free of any "proxy" configuration
  - logHttpActivity=true returns following output:
  ```bash
  2025-08-08 14:29:24,827 INFO  [org.apa.cam.com.htt.LoggingHttpActivityListener] (Camel (camel-1) thread #2 - timer://myTimer) Sending HTTP Request   (host: 6895e6b2039a1a2b2890bc40.mockapi.io route: route1 exchangeId: 17188965120DCDE-0000000000000000)
GET /api/v1/test HTTP/1.1
Accept-Encoding: gzip, x-gzip, deflate
Host: 6895e6b2039a1a2b2890bc40.mockapi.io
Connection: keep-alive
User-Agent: Apache-HttpClient/5.5 (Java/23.0.1)


2025-08-08 14:29:25,013 INFO  [org.apa.cam.com.htt.LoggingHttpActivityListener] (Camel (camel-1) thread #2 - timer://myTimer) Received HTTP Response (host: 6895e6b2039a1a2b2890bc40.mockapi.io route: route1 exchangeId: 17188965120DCDE-0000000000000000 elapsed: 165ms)
HTTP/1.1 200 OK
Access-Control-Allow-Headers: X-Requested-With,Content-Type,Cache-Control,access_token
Access-Control-Allow-Methods: GET,PUT,POST,DELETE,OPTIONS
Access-Control-Allow-Origin: *
Content-Length: 7497
Content-Type: application/json
Date: Fri, 08 Aug 2025 12:29:24 GMT
Etag: "1280644653"
Nel: {"report_to":"heroku-nel","response_headers":["Via"],"max_age":3600,"success_fraction":0.01,"failure_fraction":0.1}
Report-To: {"group":"heroku-nel","endpoints":[{"url":"https://nel.heroku.com/reports?s=%2FYI8wk1j6Os83puZ1BrW%2Be0KVcj0s100XjNiwCJJU8A%3D\u0026sid=1b10b0ff-8a76-4548-befa-353fc6c6c045\u0026ts=1754656164"}],"max_age":3600}
Reporting-Endpoints: heroku-nel="https://nel.heroku.com/reports?s=%2FYI8wk1j6Os83puZ1BrW%2Be0KVcj0s100XjNiwCJJU8A%3D&sid=1b10b0ff-8a76-4548-befa-353fc6c6c045&ts=1754656164"
Server: Heroku
Via: 1.1 heroku-router
X-Powered-By: Express
```