# Finch-simple-app

This is an app to get started using Finch. 

## Usage
To run the app locally, type on the command line:
```
sbt run
```

To run the tests:
```$xslt
sbt test
```
For the moment it has 2 endpoints:

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

## TODO

- Fix logging
- Add CI with travis
- Deploy to Heroku
- Add example with database
- Add autoformatting with scalafmt 
