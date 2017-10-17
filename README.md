

Spring4 MVC GeoIP2 REST API
===============================================================
Spring4 MVC GeoIP2 REST API with Swagger API Documentation.

##  Requirements
- STS(or maven)
- tomcat server
- GeoIP2 database (mmdb)


## Getting Started

### Downloading The Source Code

```
$ git clone https://github.com/tsungtwu/spring-geoip2-rest-api.git
```

### Downloading The Database
Format: MaxMind binary format

1. Downlaod From official website: [link](https://dev.maxmind.com/geoip/geoip2/geolite2/)

2. Copy to `src/main/resources`
3. Change mmdb path in GeoipCityService


### Deploy REST API Server

#### Run with STS
  - import as Maven project
  - change context root to "/"
  - run on server


#### Run with docker

```
$ sh deploy.sh

```


### Run On tomcat

visit http://localhost:8080/