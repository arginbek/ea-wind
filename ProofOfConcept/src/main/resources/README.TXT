# ea-wind

System Use using Postman

1) (STEP MANDATORY TO BEGIN OR INSERT USERS DIRECTLY IN DB) Login with admin user defined at Spring XML file
	- Method: POST
	- URL: http://localhost:8080/login
	- Authorization: No Auth
	- Body (x-www-form-erlencoded):
		- username: admin
		- password: admin

2) Person Microservice

	2.1) Create
		- Method: POST
		- URL: http://localhost:8080/persons
		- Authorization: No Auth
		- Body 1 (raw - JSON):
{
    "firstName": "Dave",
    "lastName": "Cross",
    "email": "dcross@mum.edu",
    "username": "cross",
    "password": "cross",
    "roles": ["ROLE_ADMIN"]
}

		- Body 2 (raw - JSON):
{
    "firstName": "John",
    "lastName": "Woods",
    "email": "jwoods@mum.edu",
    "username": "woods",
    "password": "woods",
    "roles": ["ROLE_CUSTOMER"]
}

		- Body 3 (raw - JSON):
{
    "firstName": "Ted",
    "lastName": "Down",
    "email": "tdown@mum.edu",
    "username": "ted",
    "password": "ted",
    "roles": ["ROLE_COUNSELOR"]
}

	2.2) Get One
		- Method: GET
		- URL: http://localhost:8080/persons/1
		- Authorization: No Auth
		
	2.3) Get All
		- Method: GET
		- URL: http://localhost:8080/persons
		- Authorization: No Auth
	
	2.4) Update
		- Method: PUT
		- URL: http://localhost:8080/persons/2
		- Authorization: No Auth
		- Body (raw - JSON):
{
	"id": 2,
    "firstName": "John Updated",
    "lastName": "Woods Updated",
    "email": "jwoodsupdated@mum.edu",
    "username": "woodsupdated",
    "password": "woodsupdated",
    "roles": ["ROLE_CUSTOMER"]
}

	2.5) Delete
		- Method: DELETE
		- URL: http://localhost:8080/persons/2
		- Authorization: No Auth
		- Body (raw - JSON): NONE
		
3) Session Microservice

	3.1) Create
		- Method: POST
		- URL: http://localhost:8080/sessions
		- Authorization: No Auth
		- Body (raw - JSON):
{
    "date": "2017-12-15",
    "startTime": "15:22:11",
    "duration": 120,
    "capacity": 20,
    "location": "V29",
    "counselor": 3
}

	3.2) Get One
		- Method: GET
		- URL: http://localhost:8080/sessions/1
		- Authorization: No Auth
		
	3.3) Get All
		- Method: GET
		- URL: http://localhost:8080/sessions
		- Authorization: No Auth
	
	3.4) Update
		- Method: PUT
		- URL: http://localhost:8080/sessions/1
		- Authorization: No Auth
		- Body (raw - JSON):
{
    "id": 1,
    "date": [
        2017,
        12,
        18
    ],
    "startTime": [
        12,
        30,
        10
    ],
    "duration": 240,
    "capacity": 30,
    "location": "V30",
    "counselor": 3
}

	3.5) Delete
		- Method: DELETE
		- URL: http://localhost:8080/sessions/1
		- Authorization: No Auth
		- Body (raw - JSON): NONE
		
4) Appointment Microservice

	4.1) Create
		- Method: POST
		- URL: http://localhost:8080/appointments/persons/2/sessions/1
		- Authorization: No Auth
		- Body (raw - JSON): NONE

	4.2) Delete
		- Method: DELETE
		- URL: http://localhost:8080/appointments/persons/2/sessions/1
		- Authorization: No Auth
		- Body (raw - JSON): NONE