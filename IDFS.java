import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;
 
public class IterativeDeepening
{
    private Stack<Integer> stack;
    private int numberOfNodes;
    private int depth;
    private int maxDepth;
    private boolean goalFound = false;
 
    public IterativeDeepening()
    {
        stack = new Stack<Integer>();
    }
 
    public void iterativeDeeping(int adjacencyMatrix[][], int destination)
    {
        numberOfNodes = adjacencyMatrix[1].length - 1;
        while (!goalFound)
        {
            depthLimitedSearch(adjacencyMatrix, 1, destination);
            maxDepth++;
        }
        System.out.println("\nGoal Found at depth " + depth);
    }
 
    private void depthLimitedSearch(int adjacencyMatrix[][], int source, int goal)
    {
        int element, destination = 1;
        int[] visited = new int[numberOfNodes + 1];
        stack.push(source);
        depth = 0;
        System.out.println("\nAt Depth " + maxDepth);
        System.out.print(source + "\t");
 
        while (!stack.isEmpty())
        {
            element = stack.peek();
            while (destination <= numberOfNodes)
            {
                if (depth < maxDepth)
                {
                    if (adjacencyMatrix[element][destination] == 1)
                    {
                        stack.push(destination);
                        visited[destination] = 1;
                        System.out.print(destination + "\t");
                        depth++;
                        if (goal == destination)
                        { 
                            goalFound = true;
                            return;
                        }
                        element = destination;
                        destination = 1;
                        continue;
                    }
                } else 
                {
                    break;
                }
                destination++;
            }
            destination = stack.pop() + 1;
            depth--;
        }
    }
 
    public static void main(String... arg)
    {
        int number_of_nodes, destination;
        Scanner scanner = null;
        try
        {
            System.out.println("Enter the number of nodes in the graph");
            scanner = new Scanner(System.in);
            number_of_nodes = scanner.nextInt();
 
            int adjacency_matrix[][] = new int[number_of_nodes + 1][number_of_nodes + 1];   			        
            System.out.println("Enter the adjacency matrix");
            for (int i = 1; i <= number_of_nodes; i++)
                for (int j = 1; j <= number_of_nodes; j++)
                    adjacency_matrix[i][j] = scanner.nextInt();
 
            System.out.println("Enter the destination for the graph");
            destination = scanner.nextInt();
 
            IterativeDeepening iterativeDeepening = new IterativeDeepening();
            iterativeDeepening.iterativeDeeping(adjacency_matrix, destination);
        }catch (InputMismatchException inputMismatch)
        {
            System.out.println("Wrong Input format");
        }
        scanner.close();
    }
}