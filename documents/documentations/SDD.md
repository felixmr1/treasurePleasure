## 1 Introduction

#### TODO
- Skriv mer teknsikt i 1.3 och länka variabler till ordlista
- Meningar med *...* bör ses över och bytas ut


### 1.1 Purpose
This Software Design Document provides design details for the TreasurePleasure mobile application and its implementation.

[Sec.1](#1-introduction) strive to give a overall specification of the high-level architecture \
[Sec.2](#2-system-architecture) goes into detail about the low-level architecture of the system and all of its subcomponents \
[Sec.3](#3-persistent-data-management) describes how data is managed and stored between different user sessions \
[Sec.4](#4-access-control-and-security) will reveal the different roles in the game \

### 1.2 Designgoals
The design of the code follows a [MVP](#mvp) pattern. With MVP the code is modular and decoupled. The goal with strictly following a MVP pattern is to easily expand the application with more functionality. All public components in the model has tests to make it easy to upgrade components without breaking the application.  

### 1.3 Definitions, acronyms and abbreviations
##### MVP: 
The application follows a MVP architecture. Mvp, short for model-view-presenter, the **model** stores the application logic and data. The model is responsible for the distribution and manipulation of the data and the model does not depend on anything other than its own components. To visualize the data from the model a **view** is implemented *....* The view is dumb and does not know the structure of the model, neither *should* it have any responsibility over data manipulation and logics. Since the view is dumb we need a way to communicate between the view and the model, this is why we need a middleman. The presenters job is to update the view and give jobs to the model. \

For example when a user interacts with the UI the view forwards the action to the presenter. The presenter then interprets the user action and calls the model to execute/hand the appropriate jobs/data. When the presenter retrieves the result from the model it tells the view what to display.

##### Treasure pleasure init.
Attributes that are part of the initiation of the model, e.g. Map coordinates that defines the size and real world location of the map, number of items that are always collectible on the map

##### Google maps SDK
The SDK is an API proviced by google that accesses googles servers to display a map. We can append objects on the map with given coordinates. The SDK has built in functionalities for user gestures so that the player can interract with plcaed objects.

##### Location handling
We use the GPS coordinates from a users cellphone and saves them in our location class. The reason why we dont use the built in location service is becuase its not posible to unit-test it on fake coordinates/an emulator. Defining our own location handler made it so that we could extend with more functionality e.g. checking if a given location is within an specified area. 

##### Item generation
The map always contains a certain amount of spawned items. When a item is collected by a player the model automaticliy spawns a new random item at a random location such that two items has atleast a [interaction radius](#4-access-control-and-security) between them.

##### Inventory
Backpack and tresure chest both contain a list of items, so we made a abstarct Inventory class *for this*.

##### Score calculation
Each item type has a value and each player has a value multiplier. For a more dynamic experience we made it so that a user can sell items to increase the value multiplier which increases the value of future pickups. The player class holds the multiplier and manipulates the item value when its picked up.

#### Future implementations
- **Settings** - Here one can access the highscore or change name and avatar.
- **Hall of Fame** - A list or high score of the best performing players.


## 2 System architecture

The most overall, top level description of the system. Which (how many) machines
are involved? What are the system components, and what are they responsible for?
Show the dependency between the different system components. If there are more
computing entities (machines) involved: show dow they communicate. Describe the
high level overall flow of some use stories. Describe how to start and stop the system.
Any general principles in application? Flow, creations, . . .


### 2.1 Subsystem decomposition

Describe in this section each identified system component (that you have implemented)


### 2.2 "First component"

What is this component responsible for and what does it do.
Divide the component into subsystems (packages) and describe their responsibilities.
Draw an UML package diagram for the top level. Describe the interface and
dependencies between the packages. Try to identify abstraction layers. Think about
concurrency issues.

If your application is a standalone then:

- Describe how MVC is implemented
- Describe your design model (which should be in one package and build on the
domain model)
- Give a class diagram for the design model.
otherwise:
- MVC and domain model described at System Architecture
Diagrams
- Dependencies (STAN or similar)
- UML sequence diagrams for flow.
Quality
- List of tests (or description where to find the test)
- Quality tool reports, like PMD (known issues listed here)
**NOTE**: Each Java, XML, etc. file should have a header comment: Author, responsibility,
used by ..., uses ..., etc.


### 2.3 "Next component"

As above, and continue for all components.


## 3 Persistent data management

How does the application store data (handle resources, icons, images, audio, ...).
When? How? URLs, pathes, ... data formats... naming..


## 4 Access control and security

Different roles using the application (admin, user, ...)? How is this handled?


## 5 References

