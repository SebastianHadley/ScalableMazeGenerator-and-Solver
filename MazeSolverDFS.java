import java.util.*;
import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
class MazeSolverDFS
{
  private int n,m,start_cell,end_cell;
  private String maze_data = "";
  private String openness_list;
  public static void main (String[] args)
  {
    String maze_file = args[0];
    MazeSolverDFS  mazeSolver = new MazeSolverDFS();
    mazeSolver.run(maze_file);
  }
  //Runs the program.
  private void run(String maze_file)
  {
      loadFile(maze_file);
      Solver solve = new Solver(n,m,start_cell,end_cell,openness_list);
  }

  //Loads the file and gets the information.
  public void loadFile(String maze_file)
  {
    try
    {
      int counter = 0;
      String temp;
      Scanner inputStream = new Scanner(new File(maze_file));
      inputStream.useDelimiter(",|\\:");
      while(inputStream.hasNext())
      {
        String temp_maze = inputStream.next();
        if(counter == 0)
        {
          n = Integer.parseInt(temp_maze);
        }
        else if(counter == 1)
        {
          m = Integer.parseInt(temp_maze);
        }
        else if(counter == 2)
        {
          start_cell = Integer.parseInt(temp_maze);
        }
        else if(counter == 3)
        {
          end_cell = Integer.parseInt(temp_maze);
        }
        else
        {
          openness_list = temp_maze;
        }
        counter++;
      }
      inputStream.close();
    }
    catch(Exception E)
    {
      System.out.println("Error");
    }
  }
}
