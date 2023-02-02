# aws-services
- Create Github Action secret: AWS_ACCESS_KEY_ID and AWS_SECRET_ACCESS_KEY. Value from AWS IAM > Users > <user> > Security credentials > access key
- Create Dockerfile
- Download PostgreSQL image and run container. Default connection info:
    - Host: localhost
    - Port: 55000
    - Username: postgres
    - Password: postgrespw

- Download and run PGAdmin (optional)
  - docker run -p 5050:80 -e 'PGADMIN_DEFAULT_EMAIL=pgadmin4@pgadmin.org' -e 'PGADMIN_DEFAULT_PASSWORD=admin' -d --name pgadmin4 dpage/pgadmin4
  - URL: http://localhost:5050/
  - Username: pgadmin4@pgadmin.org 
  - Password: admin

- Create dev local credential and database
- Config liquibase in build.gradle 
  - implementation 'org.springframework.boot:spring-boot-starter-data-jdbc' 
  - runtimeOnly 'org.postgresql:postgresql'
  - implementation 'org.liquibase:liquibase-core'
- Create resources/db/changelog/db.changelog-master.yaml. This will be picked by default
- Config spring data source in application.yml
- Create xml file for db change log, and include these files in db.changelog-master.yaml
- Build. The change log will be updated to DB (this is for local db).

- Configure spring doc https://springdoc.org/v2/:
  - Gradle config: implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2'
  - Change default Swagger URL in application.yml
  - Visit Swagger doc at http://localhost:8080/<name in application.yml>
  - Visit API spec at http://localhost:8080/v3/api-docs
  - Json can be viewed at https://editor.swagger.io/