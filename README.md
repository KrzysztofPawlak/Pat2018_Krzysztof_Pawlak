# Pat2018_Krzysztof_Pawlak
# build and run
in repository directory - mvn clean install

java -jar target/Pat2018_Krzysztof_Pawlak-0.0.1-SNAPSHOT.jar
# Car
## create new car - POST
example: localhost:8080/cars
```
{
	"vin": "1NPSL79X2BD128799",
	"make": "Fiat",
	"model": "126p",
	"year": 1970,
	"registrationNumber": "HQ149C3"
}
```
test: testCreateCar

expect status: 201
## get all cars - GET
example: localhost:8080/cars

test: testGetAllCar

expect status: 200
## get single car - GET
example: localhost:8080/cars/1NPSL79X2BD128799

test: findCarByVinFound

expect status: 200
## update car - PUT
example: localhost:8080/cars/1NPSL79X2BD128799
```
{
	"make": "Updated Fiat",
	"model": "Updated 126p",
	"year": 2000,
	"registrationNumber": "HQ149C3"
}
```
to update car (Vehicle Identification Number) should be exist in storage

test: testUpdateCar

expect status: 200
## delete car - DELETE
example: localhost:8080/cars/1NPSL79X2BD128799

test: testDeleteCar

expect status: 200

# Customer
## create new customer - POST
example: localhost:8080/customers
```
{
	"pesel": "53050195963",
	"name": "Barbara",
	"surname": "Kowalska",
	"dateOfBirth": 1970,
	"sex": "female"
}
```
## get all customers - GET
example: localhost:8080/customers

test: testGetAllCustomer

expect status: 200
## get single customer by pesel - GET
example: localhost:8080/customers/53050195963

test: testCustomerWithPeselExists

expect status: 200
## update customer - PUT
example: localhost:8080/customers/53050195963
```
{
	"name": "Barbara",
	"surname": "Nowak",
	"dateOfBirth": 1970,
	"sex": "female"
}
```
to update customer, pesel should be exist in storage
## delete customer - DELETE
example: localhost:8080/customers/53050195963
