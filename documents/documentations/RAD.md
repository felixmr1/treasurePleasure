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
Map - The map is the base and foreground of the whole game, other view like the Hall of Fame, Item Pickup Screen or Settings might appear on top of it.
Player - A player is any unique user using/playing the app/game
Collectables - Randomly spawned items on the map that can be picked up by players. These items is shared/same for all players so the one that picks an item up first gets it. There is always a certain number of collectables available, after one is picked up a new one is randomly spawned. Items have different value and size/weight depending on how rare they are.
Item Pickup - When an item is picked up the player is prompted with a screen with details about the item.
Settings - Here one can access the highscore or change name and avatar.
Backpack - Each player has a backpack with a capacity, when the backpack is full no more items can be picked up. The backpack can be emptied at any point at the players treasure chest.
Treasure chest - Each player gets to place a treasure chest on the map at the start of each season. It contains all of the items that has been collected and been emptied from a players backpack. When a season is over the value contained in the treasure chest is summed up and a winner is determined based of which user has the most value.
Season - A season is a specified length of e.g. 1 week. When the season has passed the winner is determined and is placed on the Hall of Fame, then a new season starts and all players progresses are reseted.
Hall of Fame - A list or high score of the best performing players.
Shop - In the shop collectables can be crafted i.e. traded to either more valuable items or items with equal value but less weight (this mechanic has not been decided). There might also be an option to buy a weapon, armory or tool.
