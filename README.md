## Pokemon-Game-Ex2-OOP
![Pokemon Header](https://i.ibb.co/sPpVhDx/Webp-net-resizeimage.png)
# Project Overview:
The project is split into two parts, the first part is a an implementation of a weighted directed graph and path finding algorithms
and the second part is a "Pokémon Game" which uses the graph and algorithm to traverse and play the game.

## The First Part
The First Part of the project implemets a graph using 2 hashmaps, a node class and an edge class.

   `private int mc_count;`
   
   `private int edge_count;`
     
  ` private HashMap<Integer, node_data> nodeMap;`
     
   `private HashMap<Integer, HashMap<node_data, edge_data>> edgesMap;`
    
The algoritem class "DWGraph_Algo" implemets path finding algoritems using Dijkstra's algorithm for finding the shortest path between two nodes in the graph.

The Dijkstra algorithm uses Weights that are positive doubles and a priority queue  
for further reading see: [https://en.wikipedia.org/wiki/Dijkstras_algorithm] (Dijkstra Wiki) or the Project Wiki.

![Dijkstra-Animation](https://i.ibb.co/c27Thp3/Dijkstra-Animation.gif)

The Graph is capable of preforming the following functions:
* `public boolean isConnected()` 
> A function that checks if there is a valid path from each node to each other node in the graph.
* `public directed_weighted_graph copy()`
> A function that computes a deep copy of the graph.
* `public double shortestPathDist(int src, int dest)`
> A function that finds the shortest path distance between 2 given nodes.
* `public List<node_data> shortestPath(int src, int dest)`
> A function that retrieves the path for 2 given nodes in List form
* `public boolean save(String file)`
  `public boolean load(String file)`
> A save and load functions in JSON form.

## The Second Part 
in the second part i implemented the pokemon game on a graph with a Login Menu.

![Login Menu](https://i.ibb.co/bztcxNy/Webp-net-resizeimage-2.png)

![Pokemon Game Demo](https://i.ibb.co/wrZt80f/Webp-net-resizeimage-1.png)

In the Pokemon game the agents are strategically located in order to catch as much pokemon's (and gain points doing so).
using the pathfinding algoritems the agents find the shortests weighted path between the pokemons in the most efficent way.

The scores that the game achieved for the player chosen level are sent to a game server.


