
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
		
		System.out.println();
		
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
	}
	
	public static void breadthFirst(int origin, Graph<String> graph)
	{	
		Bag<Integer> visited = new Bag<>();
		
		visited.add(origin);
		System.out.print(graph.getLabel(origin));
		
		int[] queue = new int[10];
		int queueCount = 0;
		boolean countdown = false;
		
		int[] neighbors = graph.neighbors(origin);		
		
		for(int i = 0; i < neighbors.length; i++)
		{
			queue[i] = neighbors[i];
			queueCount++;
			visited.add(neighbors[i]);
			System.out.print(graph.getLabel(neighbors[i]));
		}
		
		while(queueCount != 0)
		{
			neighbors = graph.neighbors(queue[0]);
			
			for(int i = 0; i < neighbors.length; i++)
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
			
			for(int i = 0; i < queue.length - 1; i++)	
				queue[i] = queue[i + 1];		

			if(countdown == true)
				queueCount--;
		}
	} //end breadthFirst
	
	public static void depthFirst(int origin, Graph<String> graph) 
	{
        Stack<Integer> stack = new Stack<>();
        Bag<Integer> visited = new Bag<>();
        
        stack.push(origin);
        System.out.print(graph.getLabel(origin));
        
        int[] neighbors = graph.neighbors(stack.peek());
        
        if(!visited.contains(neighbors[0]))
        {
        	System.out.print(graph.getLabel(neighbors[0]));
        	visited.add(neighbors[0]);
        	stack.push(neighbors[0]);
        }
        
        neighbors = graph.neighbors(neighbors[0]);
        
        int breakLoop = 0;    
        while(breakLoop != 2)
        {  
            if(!visited.contains(neighbors[0]))
            {
            	System.out.print(graph.getLabel(neighbors[0]));
            	visited.add(neighbors[0]);
            	stack.push(neighbors[0]);
            	neighbors = graph.neighbors(neighbors[0]);
            }
            else if(!stack.isEmpty())
            { 	
            	neighbors = graph.neighbors(stack.pop());
            	
            	for(int j = 0; j < neighbors.length - 1; j++)
    				neighbors[j] = neighbors[j + 1];			
            	
            	if(stack.isEmpty())
            		breakLoop++;
            }
        }	
    } //end depthFirst    
} //end GraphTraversalDriver
