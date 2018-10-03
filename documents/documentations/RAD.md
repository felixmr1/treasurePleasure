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

## 2 Requirements

### 2.1 User Stories

See Trello for user stories.

### 2.2 User interface

## 3 Domain model

### 3.1 Class responsibilites



### Future implementations
- Settings - Here one can access the highscore or change name and avatar.
- Season - A season is a specified length of e.g. 1 week. When the season has passed the winner is determined and is placed on the Hall of Fame, then a new season starts and all players progresses are reseted.
- Hall of Fame - A list or high score of the best performing players.

