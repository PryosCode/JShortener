# JShortener

Simple Java URL Shortener with easy deployment in mind.

## Download

### Docker

You can use either a [docker-compose.yml](docker-compose.example.yml) or a run command and use [pryoscode/jshortener](https://hub.docker.com/r/pryoscode/jshortener) the official Docker image.

### Java

Another way would be to directly [download](https://github.com/PryosCode/JShortener/releases)  the `JShortener.jar` and execute it with [JRE-8](https://www.oracle.com/java/technologies/javase-jre8-downloads.html). But you would need to manually install [MySQL](https://www.mysql.com/) or [MariaDB](https://mariadb.org/).

```bash
java -jar -DJS_DB_USER="jshortener" -DJS_DB_PASSWORD="0123456789" JShortener.jar
```

## Config

| VARIABLE       | DEFAULT                                 |
|----------------|-----------------------------------------|
| JS_WEB_PORT    | 80                                      |
| JS_WEB_STATUS  | 302                                     |
| JS_WEB_ROOT    | https://github.com/PryosCode/JShortener |
| JS_WEB_404     | https://github.com/PryosCode/JShortener |
| JS_DB_HOST     | 127.0.0.1                               |
| JS_DB_PORT     | 3306                                    |
| JS_DB_USER     | root                                    |
| JS_DB_PASSWORD |                                         |