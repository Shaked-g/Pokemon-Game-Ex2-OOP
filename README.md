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

The Dijkstra algorithm uses Weight that are positive doubles and a priority queue  
for further reading see: [https://en.wikipedia.org/wiki/Dijkstras_algorithm] (Dijkstra Wiki) or the Project Wiki.

![Dijkstra-Animation](https://i.ibb.co/c27Thp3/Dijkstra-Animation.gif)


## The Second Part 
in the second part i implemented the pokemon game on a graph with a Login Menu.

![Login Menu](https://i.ibb.co/bztcxNy/Webp-net-resizeimage-2.png)

![Pokemon Game Demo](https://i.ibb.co/wrZt80f/Webp-net-resizeimage-1.png)

