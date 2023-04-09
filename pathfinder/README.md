# Pathfinder library
  - Nirmal Chaudhari [chaudn12@mcmaster.ca]

## Rationale

The purpose of this library is to provide a way for the user to implement a GraphADT, where they can solve various graph computational problems.

### Nodes

- Nodes are identified by their associated `name` attribute. This attribute is represented as a string to allow more flexibility with whether the user wants to identify nodes as a word, or number.
- Nodes also have a `weight` attribute that is used to identify how important that node is. For instance, in many graph problems, different nodes can have an associated weight. This is mainly used for shortest/longest path problems.
- Nodes also have a `connections` attributes which allows the node to be in the "knowing" of which other nodes it is connected to. Similar to an adjacency list representation.
- Two nodes are deemed equivalent if they share the same name.

### Edges

- Edges are identified by their Node pairs. Two edges are deemed equivalent is N1=N1 and N2=N2.
- Edges also have an associated `weight`, for weighted graph problems. Set weight to 0 for unweighted graph problems.

### GraphADT

- The GraphADT consists of a set of Nodes and Edges. New equivalence definition for Nodes and Edges ensures that sets do not contain duplicates.
- For extension, the user also has the option of adding nodes and edges after initializing the GraphADT.
- GraphADT can be represented as two types: Directed and Undirected Graphs:

    #### UndirectedGraph
  - Undirected graph is initialized by a set of edges and nodes. 
  - Adding an edge (N1,N2) also adds the edge (N2,N1) to the set (definition of undirected).

    #### DirectedGraph
  - Directed graph is initialized by a set of edges and nodes.
  - Adding edge (N1,N2) only adds that edge to the set.

### ShortestPath
- Library contains an interface for the ShortestPath. All shortest path implementations must have a `getCost`, `getPath` and `generate` method.
- Dijkstra's shortest path algorithm is already implemented, and ready for use by any user.

    #### DijkstraPath
  - User must enter the graph (Directed or Undirected), the desired start node and end node.
  - Path is returned as a list of Edges object.
  - Cost is returned as a double value.


## Open for Extension:
- Users can implement a new shortest path algorithm by implementing the interface ShortestPath.
- The new algorithm must contain an implementation for `generate()`, that takes a GraphADT object, start node and end node.
- The new algorithm must also implement `getPath()`, which returns a list of Edge objects.
- The new algorithm must also implement `getCost()`, which returns the cost of path between start and end node.
- Important node: The new ShortestPath algorithm must use the GraphADT, Node and Edge objects specified in this library.

