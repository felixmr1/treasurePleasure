## 1 Introduction

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
The application follows a MVP architecture. Mvp, short for model-view-presenter, the **model** stores the application logic and data. The model is responsible for the distribution and manipulation of the data and the model does not depend on anything other than its own components. To visualize the data from the model a **view** is implemented *....* The view is dumb and does not know the structure of the model, neither *should* it have any responsibility over data manipulation and logics. Since the view is dumb we need a way to communicate between the view and the model, this is why we need a middleman. The presenters job is to update the view and give jobs to the model. For example when a user interacts with the UI the view forwards the action to the presenter. The presenter then interprets the user action and calls the model to execute/hand the appropriate jobs/data. When the presenter retrieves the result from the model it tells the view what to display.


##### Game definitions
- The **map** is the main view of the game. We append the players location on a map served by google. From the map a player can interact with items, shops and settings

- A **player** is one of several players on a map. Players are unique to each user. Players also hold a backpack and a treasure chest, where they can store items collected from different locations on the map.

- **Collectables** are randomly spawned items on the map. Collectables can be interacted with by players, and collected if the player is close enough to the collectable. These items are shared for all players so the one that picks an item up first gets it, and the collectable is removed from the global map. There is always a certain amount of collectables available, when one is collected a new collectable is randomly spawned.

- A **item** is a valueble in the game. There are different items and each item has a value and a unique type/id. The type can for example be diamond and gold.

- A **backpack** is a local inventory that every player carries with them. Backpacks are unique to each player and can only carry a limited amount of items in them. When a players backpack is full the player has to empty it to his/hers tresure chest.

- A **Treasure chest** is stationary inventory with unlimited size. A player has one unique treasure chest that the player choses where to place when first starting the game. When a player empty his/hers backpack into the treasure chest a total value is calculated and dispalyed.

- A **shop** is a way for the player to buy a higher value multiplier. The value mulitplier increases the value of collected items from the map.

- A **score** is a players unique player score for the current season. The score is calculated using collected items value after the player has inserted them to his/hers treasure chest. 

- **Season** is a limited duration that a player can collect items and increments his/hers score. The season is a global season for all players. The player with the highest indivdual score after a season has ended won.

### 1.1 Definitions, acronyms and abbreviations
Some definitons etc. probably same as in RAD..


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

