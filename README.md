
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

The store has three types of films.
1. New releases – Price is <premium price> times number of days rented.
2. Regular films – Price is <basic price> for the first 3 days and then <basic price>
times the number of days over 3.
3. Old film - Price is <basic price> for the first 5 days and then <basic price> times
the number of days over 5
<premium price> is 40
<basic price> is 30

The customers say when renting for how many days they want to rent for
and pay up front. If the film is returned late, then rent for the extra days is charged
when returning.
	
Customers get bonus points when renting films. A new release gives 2 points and
other films give one point per rental (regardless of the time rented).

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
