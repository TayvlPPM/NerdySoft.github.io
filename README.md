# NerdySoft.github.io
Test task for NerdySoft

## Project configuration
1. Clone this repository.
2. Build project.

RDBMS chosen is MySQL
#### Back-end
1. Change connection parameters :
    ```
    spring.datasource.url=jdbc:mysql://localhost:3306/taskmanager?createDatabaseIfNotExist=true&serverTimezone=UTC
    spring.datasource.username=<username>
    spring.datasource.password=<password>
    ```
2.Execute query before start working.
   ```
   INSERT INTO roles(name) VALUES('ROLE_USER');
   INSERT INTO roles(name) VALUES('ROLE_ADMIN');

   ```
3. Project runs in localhost:8080/api/

#### Front-end
1.Run angular project
   ```
  ng serve

   ```
2.Project runs in localhost:4200/api/

