## Concept:

A mobile multiplayer game for Android devices utilizing Google's geolocation API. It will use the mobile device GPS to locate and pinpoint players on a shared map where they can explore a virtual world and capture various objects and artifacts. Collectables are shown on the map and collected when stepped on, they have different values and can be combined and crafted into jewelries.

Each player has a backpack with a maximum capacity, when the capacity is full the backpack must be emptied at the players treasure chest before more things can be collected. Whoever has the most value contained in their treasure chest at the end of a virtual season wins that season and will appear on the “Hall of Fame”. 

Players get to place their treasure chest and jewelcraft on the map with a minimum distance (e.g. 200m) between them. This promotes fairness and whilst still encouraging physical activity.


## Epics / User Stories (example):

See Trello for user stories.

as a, 
I want, 
because:

As a player I want to see a map pinpointing my location; so that I know where in the world I’m located relative to collectable items. 

As a player I want to also see other players on my map (multiplayer); because a social game is more fun.

As a player I want the possibility to toggle view (map, highscore, settings) through e.g. a side menu or menu button in the top right. 
As a player I want the possibility of seeing what players won prior seasons (history/competitiveness)
As a player I want to set a player name and a avatar/picture that is represented on the map for other players to see (personalize)
As a developer I want to identify user id’s by either a register system or by automating a unique id for each unique device (user identification)
As a player I don’t want to see inactive players on my map (cleanliness)

## Dictionary:

- The **map** is the main view of the game. We append the players location on a map served by google. From the map a player can interact with items, shops and settings

- A **player** is one of several players on a map. Players are unique to each user. Players also hold a backpack and a treasure chest, where they can store items collected from different locations on the map.

- **Collectables** are randomly spawned items on the map. Collectables can be interacted with by players, and collected if the player is close enough to the collectable. These items are shared for all players so the one that picks an item up first gets it, and the collectable is removed from the global map. There is always a certain amount of collectables available, when one is collected a new collectable is randomly spawned.

- A **item** is a valueble in the game. There are different items and each item has a value and a unique type/id. The type can for example be diamond and gold.

- A **backpack** is a local inventory that every player carries with them. Backpacks are unique to each player and can only carry a limited amount of items in them. When a players backpack is full the player has to empty it to his/hers tresure chest.

- A **Treasure chest** is stationary inventory with unlimited size. A player has one unique treasure chest that the player choses where to place when first starting the game. When a player empty his/hers backpack into the treasure chest a total value is calculated and dispalyed.

- A **shop** is a way for the player to buy a higher value multiplier. The value mulitplier increases the value of collected items from the map.

- A **score** is a players unique player score for the current season. The score is calculated using collected items value after the player has inserted them to his/hers treasure chest. 

- **Season** is a limited duration that a player can collect items and increments his/hers score. The season is a global season for all players. The player with the highest indivdual score after a season has ended won.

### Future implementations
- Settings - Here one can access the highscore or change name and avatar.
- Season - A season is a specified length of e.g. 1 week. When the season has passed the winner is determined and is placed on the Hall of Fame, then a new season starts and all players progresses are reseted.
- Hall of Fame - A list or high score of the best performing players.

