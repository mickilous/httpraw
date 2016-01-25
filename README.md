# Java Web Based application that logs the Raw HTTP request

Just submit a request (GET, POTS, PUT, DELETE) to http://<host>:8080

The Raw HTTP Request will be logged in console by default.

### Usage
```
mvn clean install
mvn jetty:run
````
To specify another port
````
mvn jetty:run -Djetty.http.port=9999
````
