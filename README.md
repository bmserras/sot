# Synoptics of Things - Workbench

A generic tool to develop widgets and synoptics.

## Init database

```sh
docker compose -p sot-workbench-db -f database/docker-compose.yml up -d
```

Access the adminer service at http://localhost:8080 and type the following properties:
* System: PostgreSQL
* Server: postgres
* Username: sot
* Password: sot
* Database: sot-workbench

## Running the application

The project is a standard Maven project. To run it from the command line,
type `mvnw` (Windows), or `./mvnw` (Mac & Linux), then open
http://localhost:8000 in your browser.
