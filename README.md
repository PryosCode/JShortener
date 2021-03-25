<p align="center">
    <a href="https://github.com/PryosCode/JShortener/releases"><img alt="Downloads" src="https://img.shields.io/github/downloads/PryosCode/JShortener/total?label=Downloads"></a>
    <a href="https://hub.docker.com/r/pryoscode/jshortener"><img alt="Pulls" src="https://img.shields.io/docker/pulls/pryoscode/jshortener?label=Pulls"></a>
    <a href="https://github.com/PryosCode/JShortener/releases"><img alt="Build" src="https://github.com/PryosCode/JShortener/actions/workflows/gradle.yml/badge.svg"></a>
    <a href=""><img alt="License" src="https://img.shields.io/github/license/PryosCode/JShortener?label=License"></a>    
    <a href="https://discord.gg/bF2GRHq"><img alt="Discord" src="https://discord.com/api/guilds/350302354639290379/widget.png"></a>
</p>

# JShortener

Simple Java URL Shortener with easy deployment in mind.

## Download

### Docker

You can use either a [docker-compose.yml](docker-compose.example.yml) or a run command and use [pryoscode/jshortener](https://hub.docker.com/r/pryoscode/jshortener) the official Docker image.

### Java

Another way would be to directly [download](https://github.com/PryosCode/JShortener/releases) the `JShortener.jar` and execute it with [JRE-11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html).

```bash
java -jar -DJS_WEB_PORT=8080 JShortener.jar
```

## Config

| VARIABLE       | TYPE           | DEFAULT                                 |
|----------------|----------------|-----------------------------------------|
| JS_WEB_PORT    | Integer        | 80                                      |
| JS_WEB_STATUS  | Integer        | 302                                     |
| JS_WEB_ROOT    | String         | https://github.com/PryosCode/JShortener |
| JS_WEB_404     | String         | https://github.com/PryosCode/JShortener |
| JS_DB_TYPE     | sqlite / mysql | sqlite                                  |
| JS_DB_HOST     | String         | 127.0.0.1                               |
| JS_DB_PORT     | Integer        | 3306                                    |
| JS_DB_USER     | String         | root                                    |
| JS_DB_PASSWORD | String         |                                         |
| JS_VM_THREADS  | Integer        | 10                                      |