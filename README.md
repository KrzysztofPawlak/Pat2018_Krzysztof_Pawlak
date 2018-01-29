# Pat2018_Krzysztof_Pawlak
# Build and run
in repository directory
```javascript
mvn clean install
```
```javascript
java -jar target/Pat2018_Krzysztof_Pawlak-0.0.1-SNAPSHOT.jar
```
# Swagger API documentation

json: `http://localhost:8080/v2/api-docs`

ui: `http://localhost:8080/swagger-ui.html`

# Feature Toogle

to switch database

file to config : `application.properties`

H2 database: H2_STORAGE_ENABLED=true

embedded database: H2_STORAGE_ENABLED=false

# Content Negotation

data can be return to format:

json (default): `http://localhost:8080/cars?mediaType=json`

xml: `http://localhost:8080/cars?mediaType=xml`

# Car
## create new car - POST

Dodaje nowy pojazd do bazy komisu.

example: `localhost:8080/cars`
```
{
  <cylinderCapacity>1</cylinderCapacity>
  <dateOfFirstRegistration>1970-01-01T00:00:00.001Z</dateOfFirstRegistration>
  <dateOfRegistration>1970-01-01T00:00:00.001Z</dateOfRegistration>
  <make>string</make>
  <model>string</model>
  <nrOfSeats>1</nrOfSeats>
  <registrationNumber>string</registrationNumber>
  <vin>string</vin>
}
```
test: testCreateCar

expect status: 201
## get all cars - GET

Wyświetla wszystkie pojazdy znajdujące się w bazie komisu.

example: `localhost:8080/cars`

test: testGetAllCar

expect status: 200
## get single car - GET
example: `localhost:8080/cars/1NPSL79X2BD128799`

test: findCarByVinFound

expect status: 200
## update car - PUT
example: `localhost:8080/cars/1NPSL79X2BD128799`
```
{
  <cylinderCapacity>1</cylinderCapacity>
  <dateOfFirstRegistration>1970-01-01T00:00:00.001Z</dateOfFirstRegistration>
  <dateOfRegistration>1970-01-01T00:00:00.001Z</dateOfRegistration>
  <make>string</make>
  <model>string</model>
  <nrOfSeats>1</nrOfSeats>
  <registrationNumber>string</registrationNumber>
  <vin>string</vin>
}
```
to update car (Vehicle Identification Number) should be exist in storage

test: testUpdateCar

expect status: 200
## delete car - DELETE

Usuwa pojazd z bazy komisu

example: `localhost:8080/cars/1NPSL79X2BD128799`

test: testDeleteCar

expect status: 200

# Customer
## create new customer - POST
example: `localhost:8080/customers`
```
{
  "cars": [
    {
      "cylinderCapacity": 0,
      "dateOfFirstRegistration": "2018-01-29T16:26:27.484Z",
      "dateOfRegistration": "2018-01-29T16:26:27.484Z",
      "make": "string",
      "model": "string",
      "nrOfSeats": 0,
      "registrationNumber": "string",
      "vin": "string"
    }
  ],
  "dateOfBirth": "string",
  "name": "string",
  "pesel": "string",
  "sex": "male",
  "surname": "string"
}
```
## get all customers - GET
example: `localhost:8080/customers`

test: testGetAllCustomer

expect status: 200
## get single customer by pesel - GET

Wyświetla dane pojedyńczego pojazdu na podstawie numeru identyfikacyjnego.

example: `localhost:8080/customers/53050195963`

test: testCustomerWithPeselExists

expect status: 200
## update customer - PUT
example: `localhost:8080/customers/53050195963`
```
{
  "cars": [
    {
      "cylinderCapacity": 0,
      "dateOfFirstRegistration": "2018-01-29T21:21:40.375Z",
      "dateOfRegistration": "2018-01-29T21:21:40.375Z",
      "make": "string",
      "model": "string",
      "nrOfSeats": 0,
      "registrationNumber": "string",
      "vin": "string"
    }
  ],
  "dateOfBirth": "string",
  "name": "string",
  "pesel": "string",
  "sex": "male",
  "surname": "string"
}
```
to update customer, pesel should be exist in storage
## delete customer - DELETE

Aktualizuje dane pojazdu z bazy komisu.

example: `localhost:8080/customers/53050195963`
