## 1 Introduction

#### Purpose of the application:
Encourage physical activity through an competitive and enjoyable game experience.

#### Who will it benefit:
We believe the game can benefit anyone who enjoys a casual gaming experience while at the same time being outdoors. However the game will maybe mostly benefit those who aren't very physically active in their day to day life because of lack of motivation.

#### General description:
TreasurePleasure is a competitive mobile multiplayer game for Android devices. It will use the users phone GPS to locate him/her on a shared **map** where multiple **players** compete with each other by collecting the most **items**.

Each **player** has a **backpack** with a maximum capacity, when the capacity is full the backpack must be emptied at the players **chest** before more things can be collected. Whoever has the most value contained in their **chest** at the end of a **season** wins that season and will appear on the **score**.

#### In what situations can the application be used?
The application can be used to encourage a group of people to become more active by adding some flavour to their physical activity. It can also be used more casually by anyone who want to explore and compete within a virtual reality in their spare time.

### 1.1 Wordlist

- The **map** is the main view of the game. We append the players location on a map served by google. From the map a player can interact with items, shops and settings

- A **player** is one of several players on a map. Players are unique to each user. Players also hold a backpack and a treasure chest, where they can store items collected from different locations on the map.

- **Collectables** are randomly spawned items on the map. Collectables can be interacted with by players, and collected if the player is close enough to the collectable. These items are shared for all players so the one that picks an item up first gets it, and the collectable is removed from the global map. There is always a certain amount of collectables available, when one is collected a new collectable is randomly spawned.

- A **item** is a valueble in the game. There are different items and each item has a value and a unique type/id. The type can for example be diamond and gold.

- A **backpack** is a local inventory that every player carries with them. Backpacks are unique to each player and can only carry a limited amount of items in them. When a players backpack is full the player has to empty it to his/hers tresure chest.

- A **Treasure chest** is stationary inventory with unlimited size. A player has one unique treasure chest that the player choses where to place when first starting the game. When a player empty his/hers backpack into the treasure chest a total value is calculated and dispalyed.

- A **shop** is a way for the player to buy a higher value multiplier. The value mulitplier increases the value of collected items from the map.

- A **score** is a players unique player score for the current season. The score is calculated using collected items value after the player has inserted them to his/hers treasure chest. 

- **Season** is a limited duration that a player can collect items and increments his/hers score. The season is a global season for all players. The player with the highest indivdual score after a season has ended won.

#### Future implementations
- **Settings** - Here one can access the highscore or change name and avatar.
- **Hall of Fame** - A list or high score of the best performing players.


## 2 Requirements

### 2.1 Epics & User Stories

The **bold** items are Epics and the sub-items are User Stories

**1. As a player I want to see a map pinpointing my location and other objects location so that I get a feel for the virtual reality.**

    1.1. As a player, I want my location to be displayed on a map so that I know where I am.
    1.2. As a player, I want objects to have a location; so that I know where to find them.

**2. As a player I want to be able to collect and store items. - Because I'm a hoarder.**
    
    2.1 As a player, I want to be able to pickup items that I'm near; because items are valuable to me.
    2.2 As a player, I want to be able to see how many items I'm currently carrying, and how many more items I can collect before my backpack is full. - So that I can plan my walk.
    2.3 As a player, I want a storage chest, with unlimited capacity, to unload my backpack contents into. - So that I can save all my valuables.

**3. As a player I want to be able to buy upgrades for a more dynamic experience.**

    3.1 As a player, I want to be able to improve my character with drop-bonuses. This will add a sense of progress to my game experience.
    3.2 As a player, I want to be able to visit an upgrade center, where I can spend items (or item value) in exchange for bonuses.
    
**4. As a user I want to be able to customize my profile to get a more personalized experience.**

    4.1 As a player I need a player class so that I can get a backpack, chest and upgrade center.
    4.2 As a player I want a nickname and avatar. This will add flavour to the game.

**5. As a player I want to compete with other players so that I'm motivated to continue playing.**

    5.1 As a new player I want the game to have seasons with certain duration so that a winner is determined and everything resets and gives equal opportunities to all.
    5.2 As a player I want to be able to see my and others current score to see how well I'm doing.
    5.3 As a player I want to see the history of who has won previous season in case I am on that scoreboard.

### 2.2 User interface

The main view is a map where various objects are displayed. The user of the application can interact with these objects if they are close enough, if not then they have to move to get closer to them. If e.g. a player is close enough to an item that is on the map the user can click on this item to collect it. The user can also interact with his or her chest and shop, where items can be dropped of or traded for increased drop rate.

![Concept user interface](../images/concept-interface.png)

## 3 Domain model

![Domain model](../diagrammes/DomainModel/domain3.0.png)

### 3.1 Class responsibilites

