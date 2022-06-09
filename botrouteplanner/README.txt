#Botrouteplaner project
Created by Piotr Wiekiera
The project contains 10 files that can be divided into three groups:
First of all - the control class:
      Main.java, Controler.java, IputData.java
      these files prepare and control how the project works and what needs to be done in the right order,
Second - container classes
      BotMovment.java, Grid.java, Container.java, Coordinate.java
      in these files you can find an implementation of a computing domain.
Third - classes of graphs:
      Edge.java, Graph.java - classes responsible for creating a graph based on the computing domain
      DijkstraShorthestPath.java - contains the dijkstra algorithm for finding the shortest path in a graph

## How does it work
For the program to work properly, it needs 2 file names: the first one that defines the computational domain layout, the second one that contains a task for the robot.
Next, an object of the Controler class is created which contains the order of the program operation.
The names of the files are passed to the ImputData class where the data is read and the domain (grid) and "bot" to work are created.
The next step is to find the way the robot is to travel. Through the BotMovment class object, the findPath () method is launched to finally return the robot's route.
In the findPath () method, we find the coordinates of the selected product that is closest to the robot's starting position.
Then an object of the DijkstraShortestPath class is created by means of which we find 2 routes,
the first leading from the loss point to the product, and the second from the product to the receiving station.
In the setGraph () function, we create a graph between adjacent containers (grid elements)
each time checking the conditions so that the graph does not contain containers that are excluded from use (state == "O").
After all calculations are done, the code returns to the Controler class where the number of steps needed to complete the set, working time is calculated.
Finally, the results () method displays the number of steps, working time, and the coordinates of the robot's subsequent movements
