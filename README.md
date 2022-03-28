# greenfood
# jakarta-rest-api requirements

Jakarta EE 8 rest api

Need java11 and payara5

Build the project with maven

# PostgreSQL database docker setup

## Pull postgres image:

`docker pull postgres`

## Run postgres :

`docker run --name postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=1234 -p 5432:5432 -d postgres`

## Pull pgadmin :

`docker pull dpage/pgadmin4`

## Run pgadmin4 :

`docker run --name pgadmin -d -p 5050:5050 thajeztah/pgadmin4`

# SQL script:

First go on the [pgadmin interface](http://localhost:5050)
Create a postgreSQL database named __"green_food"__ on pgadmin 4 

Then Right clic on the database and click on "Query Tool..." and execute all the content of the greenfood.sql file

To see the result execute the following code
```sql
SELECT * FROM product
```
