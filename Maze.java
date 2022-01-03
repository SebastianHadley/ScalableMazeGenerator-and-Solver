//Maze
//Sebastian Hadley
//NxM Depth first search to generate maze
//Create grid of size nxmm random vertex in top left as start. vertex 1
//Some Comments are left in from debugging as i want to work further on the project later.
import java.util.*;
import java.util.Random;
import java.util.Stack;
public class Maze
{
  //Class Variables
  private int m;
  private int n;
  private int visited_cells;
  private Pair start_node;
  private Pair end_node;
  private int startCell;
  private int endCell;
  private int vertices;
  private Stack<Pair> past_visit;
  private Cell[][] maze;
  //Specific Constructor
  public Maze(int x, int y)
  {
    n = x;
    m = y;
    end_node = new Pair(0,0);
    visited_cells = 0;
    maze = new Cell[n+2][m+2];
    vertices = n*m;
    past_visit = new Stack<Pair>();
    buildMaze();
    fillMaze();
    printMaze();
  }
  //Builds the maze with all walls filled in.
  public void buildMaze()
  {
    for(int i = 0; i < n+2; i++)
    {
      for(int a = 0; a < m+2; a++)
      {
        Cell temp = new Cell();
        maze[i][a] = temp;
        // System.out.println(maze[i][a].getWalls());
        if(i == 0 || i == n+1 || a == 0 || a == m+1)
        {
          //System.out.println("HI"+i+a);
          maze[i][a].visit();
        }
      }
    }
    int cell = 0;
    for(int a = m; a > 0; a--)
    {
      for(int i = 1; i < n+1; i++)
        {
          cell++;
          maze[i][a].setCellNo(cell);
        }
    }
  }
  //Fills the maze with a path and an end point.
  public void fillMaze()
  {
    Random rand = new Random();
    int start_random = rand.nextInt(vertices);
    Pair temp = new Pair();
    //Adds one because that includes 0 and not the final number;
    start_random++;
    for(int a = m; a > 0; a--)
    {
      for(int i = 1; i < n+1; i++)
        {
          if(maze[i][a].getCellNo() == start_random)
          {
            start_node = new Pair(i,a);
            startCell = maze[i][a].getCellNo();
            fillMaze(i,a);
            break;
          }
        }
        if(visited_cells > 0)
        {
          break;
        }
    }
  }

  //Recursively builds the mazes path.
  public void fillMaze(int a, int b)
  {
    System.out.println("Cell"+a+b);
    int x = a;
    int y = b;
    Pair temp = new Pair();

    if(maze[x][y].getVisit() == false)
    {
      maze[x][y].visit();
      visited_cells++;
      temp.setPair(x,y);
      past_visit.push(temp);
    }

    while(visited_cells < vertices)
    {
      ArrayList<String> options = new ArrayList<String>();
      if (maze[x][y+1].getVisit() == false)
      {
      	options.add("N");
      }
      if (maze[x][y-1].getVisit() == false)
      {
      	options.add("S");
      }
      if (maze[x-1][y].getVisit() == false)
      {
      	options.add("W");
      }
      if (maze[x+1][y].getVisit() == false)
      {
      	options.add("E");
      }

      if(options.size() > 0)
      {
        Random rand = new Random();
        int chooser = rand.nextInt(options.size());
        String choice = options.get(chooser);
        switch(choice)
        {
          case "N":
            if(maze[x][y+1].getWalls() != 0)
            {
              maze[x][y+1].setWalls(3);
            }
            else
            {
              maze[x][y+1].setWalls(2);
            }
            fillMaze(x,y+1);
            break;
          case "S":
            if(maze[x][y].getWalls() != 0)
            {
              maze[x][y].setWalls(3);
            }
            else
            {
              maze[x][y].setWalls(2);
            }
            fillMaze(x,y-1);
            break;
          case "W":
            if(maze[x-1][y].getWalls() != 0)
            {
              maze[x-1][y].setWalls(3);
            }
            else
            {
              maze[x-1][y].setWalls(1);
            }
            fillMaze(x-1,y);
            break;
          case "E":
          if(maze[x][y].getWalls() != 0)
          {
            maze[x][y].setWalls(3);
          }
          else
          {
            maze[x][y].setWalls(1);
          }
          fillMaze(x+1,y);
          break;
        }
      }
      else
      {
        temp = past_visit.pop();
        fillMaze(temp.getX(),temp.getY());
        break;
      }
    }
    if(end_node.getX() == 0)
    {
      end_node.setPair(x,y);
      endCell = maze[x][y].getCellNo();
    }
  }

  //-For Debugging and Later to make into a runnable game for younger siblings.
  public void printMaze()
  {
    return;
  }

  //returns the mazes values for the main file to use.
  public String returnMaze()
  {
    String maze_string = "";
    maze_string = n+","+m+":"+startCell+":"+endCell+":";
    for(int a = m; a > 0; a--)
    {
      for(int i = 1; i < n+1; i++)
        {
          maze_string = maze_string + maze[i][a].getWalls();
        }
    }
    System.out.println(maze_string);
    return maze_string;
  }

}
