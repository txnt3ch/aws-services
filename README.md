# aws-services
- TO use GitHub action to push to ECR: Create Github Action secret: AWS_ACCESS_KEY_ID and AWS_SECRET_ACCESS_KEY. Value from AWS IAM > Users > <user> > Security credentials > access key
- Otherwise, to go ECR > repo > view push commands to push from local
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
- Create JPA classes. Ref: https://github.com/spring-guides/gs-accessing-data-jpa
- Build. The change log will be updated to DB (this is for local db).

- Configure spring doc https://springdoc.org/v2/:
  - Gradle config: implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2'
  - Change default Swagger URL in application.yml
  - Visit Swagger doc at http://localhost:8080/<name in application.yml>
  - Visit API spec at http://localhost:8080/v3/api-docs
  - Json can be viewed at https://editor.swagger.io/



**DEPLOYMENT**
- For simplicity, this tutorial will not deploy the app using argocd but k8s deployment
  - Create k8s/awsservices-deployment.yaml and k8s/awsservices-service.yaml
  - ========== START Clean up ==============================
- #Delete namespace
  - kubectl delete namespace awsservices-app

- #Delete cluster
  - eksctl delete cluster --name awsservices-cluster --region ap-southeast-1

- #Delete RDS
- #Delete MSK

#========== END Clean up ==============================


#===== Create cluster
- eksctl create cluster --name awsservices-cluster --region ap-southeast-1 --instance-types <instance type>

#===== Create application namespace
- kubectl create namespace awsservices-app


- Create RDS  https://aws.amazon.com/getting-started/hands-on/create-connect-postgresql-db/
  - Note that for demo purpose, the DB instance need to setup for public access, as well as the VPC security group (e.g. inbound rule) must be configured for public access if we need to use DBMS tool from laptop
- Create configMap and secrets, udpate with RDS credential For secreit, need to use stringData instead of data so no need to do encoding the value
  - kubectl apply -f ./k8s/env-configmap.yml
  - kubectl apply -f ./k8s/env-secrets.yml
    - Note that we need to set the namespace in configmap/secret yml files



#===== Apply deployment & service manifest, or check the argocd/apps repo
- kubectl apply -f ./k8s/awsservices-deployment.yaml
- kubectl apply -f ./k8s/awsservices-service.yaml
- Check pod status: 
  - kubectl get pod -o wide --namespace awsservices-app
- #Run shell on a pod - to replace with pod ID. Then curl the localhost:8080/<url>
  - kubectl exec -it <pod ID> -n awsservices-app -- /bin/bash


KAFKA - LOCAL
- Ref:
  - https://codenotfound.com/spring-kafka-consumer-producer-example.html
  - https://github.com/code-not-found/spring-kafka

- Create docker-compose.yml to setup local Kafka
- Run: docker compose up -d
- Update application.yml with Kafka config
- Run command to create topic "notification"

```
docker compose exec broker \
  kafka-topics --create \
    --topic notification \
    --bootstrap-server localhost:9092 \
    --replication-factor 1 \
    --partitions 1
```

- Create config, consumer and receiver classes in kafka package
- Create KafkaController, then update Receiver.java to save notification to database


