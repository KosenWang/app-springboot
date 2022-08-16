# SW-distributed-system

Software focus: New application for the ED (emergency department) of the Charité.

## Gateway
All HTTP-Requests are sent to the gateway. The gateway redirects the request to
the corresponding service. To redirect the requests, all services need to be registered
within a *service discovery module*. As *service discovery module* we use Eureka from
Spring Cloud.

## Eureka
Eureka is a service discovery module from Spring Cloud. Each service (the gateway too)
needs to be registered within Eureka, so the gateway can redirect the requests.

# Doctor-Service
Docotor-service provides:
Doctor: create, delete, query and update
Work: create, delete
Center: create, delete, query

Servies so far runs only with error status:500 internal server error 
You may know more about it?

## API
Will probably change later

### Doctor
#### Create Doctor
| | Value |
| ----------- | ----------- |
| URL| http://localhost:8082/doctor/create|
| Method| POST |
| Input | {"firstname": "Anton","surname": "Mustermann", "email": "Anton@Muster.com"} |
| Output| Message of success or fail|
#### Delete Doctor
| | Value |
| ----------- | ----------- |
| URL| http://localhost:8082/doctor/delete/{id}|
| Method| Get |
| Input |  |
| Output| Message of success or fail|
#### Query Doctor's Information
| | Value |
| ----------- | ----------- |
| URL| http://localhost:8082/doctor/query|
| Method| POST |
| Input | {"firstname": "Max","surname": "Mustermann"} |
| Output| Patient information in JSON format {"d_id", "firstname","surname","email"}|
#### Update Doctor's Email
| | Value |
| ----------- | ----------- |
| URL| http://localhost:8082/doctor/update|
| Method| POST |
| Input | {"firstname": "Max","surname": "Mustermann", "email": "Max2@Muster.com"} |
| Output| Message of success or fail|

### Workplace Doctor relation
remind that Doctor and Center has to be not empty
create Work does not check if both (doctor, center are correct)
#### Create Work
| | Value |
| ----------- | ----------- |
| URL| http://localhost:8082/work/create|
| Method| POST |
| Input | {"d_id": 0,"c_id": 0} |
| Output| Message of success or fail|
#### Delete Work
| | Value |
| ----------- | ----------- |
| URL| http://localhost:8082/work/delete|
| Method| Get |
| Input | {"d_id": 0,"c_id": 0} |
| Output| Message of success or fail|


### Location
#### Create center
| | Value |
| ----------- | ----------- |
| URL| http://localhost:8082/center/create|
| Method| POST |
| Input | {"name": "Steglitz", "location":"Schlossstraße"} |
| Output| Message of success or fail|
#### Delete location
| | Value |
| ----------- | ----------- |
| URL| http://localhost:8082/center/delete/{id}|
| Method| Get |
| Input |  |
| Output| Message of success or fail|
#### Query Location's Information
| | Value |
| ----------- | ----------- |
| URL| http://localhost:8082/center/query|
| Method| POST |
| Input | {"name":"Mitte"} |
| Output| Patient information in JSON format {"l_id", "name","location"}|

## Patient-Service
Patient-service provides create, delete, update and query functions.
### API
Current requestbody and responsebody are only for presentation. A common response type might be needed later to communicate with client.
#### Create Patient
| | Value |
| ----------- | ----------- |
| URL| http://localhost:8081/patient/create|
| Method| POST |
| Input | {"firstname": "Foo","surname": "Wang", "email": "wang@example.com"} |
| Output| Message of success or fail|
#### Delete Patient
| | Value |
| ----------- | ----------- |
| URL| http://localhost:8081/patient/delete/{id}|
| Method| Get |
| Input |  |
| Output| Message of success or fail|
#### Query Patient'f Information
| | Value |
| ----------- | ----------- |
| URL| http://localhost:8081/patient/query|
| Method| POST |
| Input | {"firstname": "Foo","surname": "Wang", "email": "wang@example.com"} |
| Output| Patient information in JSON format {"p_id", "firstname","surname","email"}|
#### Update Patient's Email
| | Value |
| ----------- | ----------- |
| URL| http://localhost:8081/patient/update|
| Method| POST |
| Input | {"firstname": "Foo","surname": "Wang", "email": "wang@example.com"} |
| Output| Message of success or fail|

## Feedback-Service
Feedback-service now provides create and query functions for feedback and a demo insert function for guidance.
### API
Current requestbody and responsebody are only for presentation. A common response type might be needed later to communicate with client.
#### Create Feedback
| | Value |
| ----------- | ----------- |
| URL| http://localhost:8084/feedback/create|
| Method| POST |
| Input | {"g_id": 0,"feedback": "good"} |
| Output| "Submit feedback successfully!" |
#### Query Feedback
| | Value |
| ----------- | ----------- |
| URL| http://localhost:8084/feedback/query/{g_id}|
| Method| Get |
| Input |  |
| Output| List< Feedback > feedbacks|
#### Add Guidance
| | Value |
| ----------- | ----------- |
| URL| http://localhost:8084/feedback/guidance |
| Method| POST |
| Input | {"g_id": 0, "guidance": "go to see doctor"} |
| Output| "Create guidance successfully!" or "Guidance exists!"|

## Diagnosis-Service
Diagnosis-service provides create diagnosis and guidance functions and query guidance via patient information.
### API
Current requestbody and responsebody are only for presentation. A common response type might be needed later to communicate with client.
#### Create Diagnosis
| | Value |
| ----------- | ----------- |
| URL| http://localhost:8083/diagnosis/create |
| Method| POST |
| Input | {"dia_id": 123, "patientId": 0, "doctorId":1} |
| Output| "Create diagnosis successfully!" or "Diagnosis exists!" |
#### Create Guidance
| | Value |
| ----------- | ----------- |
| URL| http://localhost:8083/guidance/create |
| Method| POST |
| Input | {"dia_id": 123, "guidance": "go to house doctor", "priority": "urgent"} |
| Output| "Create guidance successfully!" or "Guidance exists!" |
#### Query Guidance
| | Value |
| ----------- | ----------- |
| URL| http://localhost:8083/guidance/query |
| Method| POST |
| Input | {"firstname": "Foo","surname": "Wang"} |
| Output| Guidance in Json format |
#### Get standard guidance according type of illness
| | Value |
| ----------- | ----------- |
| URL| http://localhost:5503/diagnosis/advice |
| Method| POST |
| Input | {"type": "Schnittwunde"} |
| Output| ["guidance01", "guidance02", "guidance03" ...] |
=======
# SW-distributed-system
Repository for the software project „Distributed System“

## Start of the system
- You need docker
- You need WSL (Windows Subsystem für Linux)

Then go into the directory of the whole project.
Command in cmd docker-compose up -d --build

The service starts with the address http://localhost:8080/
