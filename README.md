[![Build Status](https://travis-ci.com/alodavi/finch-simple-app.svg?branch=master)](https://travis-ci.com/alodavi/finch-simple-app)
# Finch-simple-app

This is an app to get started using Finch. This app has been configured to run on Heroku: check https://finch-simple-app.herokuapp.com/hello .

## Set up

Make sure you have Postgres installed and then run the following scripts to fill the database:

```$xslt
$ curl -O https://raw.githubusercontent.com/tpolecat/doobie/series/0.5.x/world.sql
$ psql -c 'create user postgres createdb'
$ psql -c 'create database world;' -U postgres
$ psql -c '\i world.sql' -d world -U postgres
$ psql -c 'create database worldtest;' -U postgres
$ psql -c '\i world.sql' -d worldtest -U postgres
```

This dataset and sample data has been taken from the book of doobie: https://tpolecat.github.io/doobie/docs/01-Introduction.html

## Usage
To run the app locally, type on the command line:
```
sbt run
```

To run the tests:
```$xslt
sbt test
```
For the moment it has these endpoints:

### /hello

Example request:

```$xslt
curl  --request GET http://localhost:8081/hello
```

Example response 

```$xslt
{"message":"Hello, world!"}
```

### /time

Example request: 

```$xslt
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"language":"de","country":"DE"}' \
  http://localhost:8081/time
```

Example response:

```$xslt
{"locale":{"language":"de","country":"DE"},"time":"Fri Mar 29 17:04:34 CET 2019"}
```

### /countries

Example request:

```$xslt
curl  --request GET http://localhost:8081/countries
```

Example response (very long list):

```$xslt
[{"code":"AFG","name":"Afghanistan","pop":22720000,"gnp":5976.00},{"code":"NLD","name":"Netherlands","pop":15864000,"gnp":371362.00},{"code":"ANT","name":"Netherlands Antilles","pop":217000,"gnp":1941.00}, ...]
```

## TODO

- Fix logging
