# aws-services

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