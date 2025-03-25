# cinemaBookingSystem
Case study for console run Cinema Booking System

**Design -** I have tried to keep the design simple and scalable. We have a 'Hall' abstract
class and that is the core of our solution. 'CinemaHall' concrete class extends 'Hall'
and is used to simulate the CinemaBookingSystem. BookingService has all the required
methods that are used to update the hall and make bookings. There are validators and util
classes to do generic stuff.
I used a 2D integer array to represent the hall and use a Map of Bookings to keep
track of all bookings and the seats reserved per booking. Array is only updated on 
confirmation of a booking. We have a flow handler to handle the booking flow and traverse
to and fro.

**Scalability -** The design allows to scale easily by adding more concrete classes and
have separate BookingService Impls in case required. We have a service factory where we
can add new service impls for usage.


**Assumptions -** I have made the following assumptions while designing the solution-
1- Movie name does not have any spaces.
2- You have Java17+ on your end
3- If custom selection is unable to sit all the people, program throws error and returns.


**How to run -** It's a console application that requires input from users.
Checkout this GIT project or download zip file and extract the content.
Import the project as a maven project on any IDE of your choice (Intellij / Eclipse).
Build the project using maven lifecycle 'compile'; run mvn compile. This should compile
the project.
In order to run the application locate the file CinemaBooking.java and run main method.
You should start seeing the statements as per the document provided. Enjoy!!

Can use mvn lifecycle 'test' phase to run tests.