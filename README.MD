Vehicle Survey Coding Challenge
===================

Challenge statement
-----------

A small city government recently bought a vehicle counter. In order for the 
vehicle counter to work, pneumatic rubber hoses are stretched across the road. Data is produced by the vehicle counter as traffic drives across the hoses. The city government requires a program to interpret the data that the machine produces.

The data from the machine looks like this:

A268981
A269123
A604957
B604960
A605128
B605132
A1089807
B1089810
A1089948
B1089951

The numbers are the number of milliseconds since midnight when the mark 
occurred. Thus, the first line above represents a pair of tires driving by at
 12:04:28am. The second line represents another pair of tires going by 142ms 
 later (almost certainly the 2nd axle of the car).

The vehicle counter has two pneumatic rubber hoses - one stretches across 
both lanes of traffic, and one goes just across traffic in one direction. 
Each hose independently records when tires drive over it. As such, cars going
 in one direction (say, northbound) only record on one sensor (preceded with 
 an 'A'), while cars going in the direction (say, southbound) are recorded on
  both sensors. Lines 3-6 above represent a second car going in the other 
  direction. The first set of tires hit the 'A' sensor at 12:10:04am, and 
  then hit the 'B' sensor 3ms later. The second set of tires then hit the 'A'
   sensor 171ms later, and then the 'B' sensor 4ms later.
 
 The machine was left to run for 5 days in a row (starting on a Monday). This
  is obvious because the times in the data make several sudden drops:
  
A86328771 B86328774 A86328899 B86328902 A582668 B582671 A582787 B582789

The city has asked you to see how many analysis features you can provide:

• Total vehicle counts in each direction: morning versus evening, per hour, 
per half hour, per 20 minutes, and per 15 minutes.
• The above counts can be displayed for each day of the session, or you can 
see averages across all the days.
• Peak volume times.
• The (rough) speed distribution of traffic.
• Rough distance between cars during various periods.

Luckily for you, you know that:
• The speed limit on the road where this is recorded is 60kph (that doesn't 
mean that everyone drives this speed, or that no one exceeds it, but it's a good starting point).
• The average wheelbase of cars in the city is around 2.5 metres between axles.
• Only 2-axle vehicles were allowed on this road during the recording sessions.

This coding challenge should be accompanied with a data file containing the 
full vehicle survey data to be analysed by the program.

Deliverables
-----------
• An explanation that is no more than one page explaining why you selected 
the chosen problem and describing your design and approach  
• A working solution to the problem test-driven in Java. The resulting 
production code should demonstrate good OOP sills. Whilst the production code
 should not rely on any third party libraries other than the Java SDK, you are encouraged to use whatever third party testing libraries you deem as desirable  
• Clear and concise instruction on how to execute the solution to the 
challenge  
• Test Driven Development is a major component and is expected

Why this problem has been chosen
--------------------------------

TODO

Design
------
This is a simple Java based application that demonstrates Test Driven 
Development. This code does not depend on any third party libraries and 
frameworks other than JUnit and Mockito for test scope. 

Some of the design considerations:  
•  Application follows OOP and KISS principles and every class is modular, 
simple, built for a single purpose, easily testable and readable  
• Clear and concise naming conventions are used both in source and tests for 
ease of understanding even without much java doc  
•  No GUI has been used as its not a requirement  
•  Not all analysers are developed as the quality/TDD agile slider 
seems to be a priority over scope. MVP :)  
•  As per the requirements source data file is accompanied with the 
application for analysis  
•  Analysis results are returned as objects and also printed to console  
•  Predicates work so good for testing with TDD.  
•  Used Factory pattern for Analyser implementations to promote loose 
coupling and ease of adding any new analysers.  
•  Entry to the application is through the Application main method that does 
very little and delgates off to VehicleSurveyor. This enables ease of 
testing of Application class and serves the purpose of the Application main 
method.  
•  Used Mockito for mocking dependencies to ensure faster test execution and 
to concentrate on a single unit of functionality 

Requirements
------------
• Java 8 
• Maven 3.3.9

How to build and run the application
------------------------------------
• git clone source from github - `git clone `     
• run `cd vehiclesurvey`  
• build `mvn clean install`  
• run `java -jar target\vehicle-survey-1.0-SNAPSHOT.jar`


