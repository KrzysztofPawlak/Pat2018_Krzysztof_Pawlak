# Pat2018_Krzysztof_Pawlak

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

expect status: 201
## delete car - DELETE
example: localhost:8080/cars/1NPSL79X2BD128799

test: testDeleteCar

expect status: 200
