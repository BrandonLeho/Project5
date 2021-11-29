
public class GraphTraversalDriver 
{

	public static void main(String args[])
	{
		int length = 9;
	
		Graph<String> graph = new Graph<>(length);
		
		graph.addEdge(0, 1);
		graph.addEdge(0, 3);
		graph.addEdge(0, 4);
		graph.addEdge(1, 4);
		graph.addEdge(2, 1);
		graph.addEdge(3, 6);
		graph.addEdge(4, 5);
		graph.addEdge(4, 7);
		graph.addEdge(5, 7);
		graph.addEdge(5, 2);
		graph.addEdge(6, 7);
		graph.addEdge(7, 8);
		graph.addEdge(8, 5);
		
		graph.setLabel(0, "A");
		graph.setLabel(1, "B");
		graph.setLabel(2, "C");
		graph.setLabel(3, "D");
		graph.setLabel(4, "E");
		graph.setLabel(5, "F");
		graph.setLabel(6, "G");
		graph.setLabel(7, "H");
		graph.setLabel(8, "I");
		
		System.out.print("Breadth-First Traversal order: ");
		breadthFirst(0, graph);
		
		System.out.println();
		
		System.out.print("Depth-First Traversal order: ");
		depthFirst(0, graph);
		
		System.out.println("\n");
		
		//output for Adjacency Matrix
		boolean[][] adjacent = new boolean[length][length];
		
		for(int i = 0; i < length; i++)
			System.out.print("\t" + graph.getLabel(i));
		
		System.out.println();
		
		for(int i = 0; i < length; i++)
		{	
			System.out.print(graph.getLabel(i));
			
			for(int j  = 0; j < length; j++)
			{
				adjacent[j][i] = graph.isEdge(i, j);
				System.out.print("\t" + adjacent[j][i]);
			}
			System.out.println();
		}
		
		System.out.println();
		
		//output for Adjacency List
		for(int i = 0; i < length; i++)
		{
			System.out.print("Adjacency List for vertex " + graph.getLabel(i) + ":\nhead");
			
			for(int j = 0; j < length; j++)
				if(graph.isEdge(i, j))
					System.out.print(" -> " + graph.getLabel(j));
			
			System.out.println("\n");
		}
	} //end main
	
	public static void breadthFirst(int origin, Graph<String> graph)
	{	
		Bag<Integer> visited = new Bag<>();	//Creates new bag to store visited vertices
		
		visited.add(origin);
		System.out.print(graph.getLabel(origin));
		
		int[] queue = new int[10];	//Array to hold vertices that have not been the front vertex yet
		int queueCount = 0;			//Counts queue position
		boolean countdown = false;	//Determine whether to shorten the queue or not
		
		int[] neighbors = graph.neighbors(origin);	//Assigns vertex's neighbors to neighbors array	
		
		for(int i = 0; i < neighbors.length; i++)	//Add origin's neighbors to queue and visited
		{
			queue[i] = neighbors[i];
			queueCount++;
			visited.add(neighbors[i]);
			System.out.print(graph.getLabel(neighbors[i]));
		}
		
		while(queueCount != 0) //while queue isn't empty
		{
			neighbors = graph.neighbors(queue[0]);
			
			for(int i = 0; i < neighbors.length; i++)	//visit the origin's neighbor's neighbors and count these as visited
			{
				if(!visited.contains(neighbors[i]))
				{
					queue[queueCount] = neighbors[i];
					queueCount++;
					countdown = true;
					visited.add(neighbors[i]);
					System.out.print(graph.getLabel(neighbors[i]));		
				}
			}
			
			for(int i = 0; i < queue.length - 1; i++)	//shorten queue by one
				queue[i] = queue[i + 1];		

			if(countdown == true)
				queueCount--;
		}
	} //end breadthFirst
	
	public static void depthFirst(int origin, Graph<String> graph) 
	{
        Stack<Integer> stack = new Stack<>();	//Stack vertices to visit neighbors
        Bag<Integer> visited = new Bag<>();		//Bag to hold visited vertices
        
        stack.push(origin);
        System.out.print(graph.getLabel(origin));
        
        int[] neighbors = graph.neighbors(stack.peek()); //Assigns origin's neighbors to neighbors array
        
        if(!visited.contains(neighbors[0]))	//Add origin's neighbors to top of the stack and visited
        {
        	System.out.print(graph.getLabel(neighbors[0]));
        	visited.add(neighbors[0]);
        	stack.push(neighbors[0]);
        }
        
        neighbors = graph.neighbors(neighbors[0]);
        
        int breakLoop = 0;    
        while(breakLoop != 2) 
        {  
            if(!visited.contains(neighbors[0])) //Visit the nighbor's neighbors and so on.
            {
            	System.out.print(graph.getLabel(neighbors[0]));
            	visited.add(neighbors[0]);
            	stack.push(neighbors[0]);
            	neighbors = graph.neighbors(neighbors[0]);
            }
            else if(!stack.isEmpty())	//If vertex has no neighbors, backtrack one vertex and search for any missed neighbors
            { 	
            	neighbors = graph.neighbors(stack.pop());
            	
            	for(int j = 0; j < neighbors.length - 1; j++) //look for missed neighbors by canceling out the first neighbor option
    				neighbors[j] = neighbors[j + 1];			
            	
            	if(stack.isEmpty())
            		breakLoop++;
            }
        }	
    } //end depthFirst    
} //end GraphTraversalDriver
