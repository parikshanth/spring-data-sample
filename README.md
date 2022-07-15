# spring-data-sample
A sample learning project to show SQL Server and Redis connection in a Spring Boot Application

The SQL Server and Redis have been setup to run in containers

`
docker run -e 'ACCEPT_EULA=Y' -e 'MSSQL_SA_PASSWORD=<password>' -p 1433:1433 -v <local-folder>:/var/opt/mssql/data -v <local-folder>:/var/opt/mssql/log -v <local-folder>:/var/opt/mssql/secrets -d mcr.microsoft.com/mssql/server:2022-latest
`

`
docker run -e REDIS_PASSWORD=<password> -p 6379:6379 --name redis -d -v <local-folder>:/bitnami/redis/data bitnami/redis:latest
`

The application uses a custom config class to load the connection details from the application.yml file
the Redis and SQL Server beans have been given a custom name