
Video Rental Store
==================

## Technology stack

Video Rental Store service has been built using the following technology stack:
- java8
- spring boot
- lombok

and uses in-memory data storage.

The exposed resources are: customers, films, orders and rentals.
API exposes operation for :
- renting one ore several film and calculating the price
- returning films and calculating possible surcharges
- retrieving films, customers, orders, rentals for particular order

### Running it locally 
There are two ways application can be run locally,

#### Using Maven
    mvn spring-boot:run -Dspring.profiles.active=development

#### Using IntelliJ
    1. Configure the gateway main Spring boot application to Run as Spring-boot application
    2. Edit application configuration to specify "VM options" as follows
     -Dspring.profiles.active=development
	 
	 
   To check if the server is up, navigate from your browser to the URLs:
   http://localhost:8080/v1/films   
   http://localhost:8080/v1/customers
