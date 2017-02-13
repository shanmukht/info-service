# Employee INFO CRUD operations - REST service

## Usage

##### All Records:
	Method: GET
	Url: https://info-service.cfapps.io/employee/all

##### Create:
	Method: POST
	Url: https://info-service.cfapps.io/employee/add
	PayLoad:
		{
			"name":"shanmukh",
			"projectName":"Gap-NgPOS"
		}

##### Read:
	Method: GET
	Url: https://info-service.cfapps.io/employee/<<employee ID>>

##### Delete:
	Method: DELETE
	Url: https://info-service.cfapps.io/employee/delete/<<employee ID>>

