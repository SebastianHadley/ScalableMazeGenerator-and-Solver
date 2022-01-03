//Sebastian Hadley c3349742
//Maze Assignment Solver class.
import java.util.*;
import java.util.Random;
import java.util.Stack;
public class Solver
{
  Random rand;
  //Decided to use a single dimensional array to represent the matrix to make it easier to work with.
  Cell[] maze;
  String move;
  long startTime,endTime;
  ArrayList<String> options;
  Stack<Integer> path;
  int step_total;
  int x,y,startCell,endCell;
  String openness_list;
  //Specific Constructor
  public Solver(int n,int m,int start,int end,String walls)
  {
    x = n;
    y = m;
    move = "";
    rand = new Random();
    options = new ArrayList<String>();
    path = new Stack<Integer>();
    maze = new Cell[x*y+1];
    startCell = start;
    endCell = end;
    openness_list = walls;
    int counter = 0;
    for(int i = 0; i < (x*y)+1; i++)
    {
      int tempwalls;
      if(i == 0 )
      {
        continue;
      }
      else
      tempwalls = Character.getNumericValue(openness_list.charAt(i-1));
      if(i == startCell)
      {
        maze[i] = new Cell(tempwalls,i,true,false);
      }
      else if(i == endCell)
      {
        maze[i] = new Cell(tempwalls,i,false,true);
      }
      else
      maze[i] = new Cell(tempwalls,i,false,false);
      {
      }
    }
    startTime = System.currentTimeMillis();
    Go();
  }
  //initial go call.
  public void Go()
  {
    maze[startCell].visit();
    path.push(startCell);
    move = randomMove(startCell);
    switch(move)
    {
      case "N":
      Go(startCell-x);
      break;
      case "E":
      Go(startCell+1);
      break;
      case "S":
      Go(startCell + x);
      break;
      case "W":
      Go (startCell -1);
      break;
    }
  }
  //recursive go function passes through the maze.
  public void Go(int i)
  {
    if(i != endCell)
    {
      if(maze[i].getVisit() == false)
      {
        maze[i].visit();
        path.push(i);
      }
      move = randomMove(i);
      switch(move)
      {
        case "N":
        Go(i-x);
        break;
        case "E":
        Go(i+1);
        break;
        case "S":
        Go(i + x);
        break;
        case "W":
        Go (i -1);
        break;
        case "NA":
        if(path.peek() == i)
        {
          path.pop();
        }
        i = path.peek();
        Go(i);
      }
    }
    path.push(i);
    endTime = System.currentTimeMillis();
    Stack<Integer> reverse = new Stack<Integer>();
    int size = path.size();
    for(int m = 0; m < size; m ++)
    {
      reverse.push(path.pop());
    }
    for(int m = 0; m < size; m++)
    {
      int temp = reverse.pop();
      System.out.print(temp+",");
    }
    System.out.println("");
    System.out.println(size-1);
    System.out.println(endTime-startTime);

    System.exit(0);
  }
  //Calculates the move for the dfs.
  public String randomMove(int i)
  {
    if(maze[i].getWalls() == 1)
    {
      if(maze[i+1].getVisit() == false)
      {
        options.add("E");
      }
    }
    else if(maze[i].getWalls() == 2)
    {
      if(maze[i+x].getVisit() == false)
      {
        options.add("S");
      }
    }
    else if (maze[i].getWalls() == 3)
    {
      if(maze[i+x].getVisit() == false)
      {
        options.add("S");
      }
      if(maze[i+1].getVisit() == false)
      {
        options.add("E");
      }
    }
    if(i > x)
    {
      if(maze[i-x].getWalls() > 1)
      {
        if(maze[i-x].getVisit() == false)
        {
          options.add("N");
        }
      }
    }
    if(i > 1)
    {
      if(maze[i-1].getWalls() == 1 || maze[i-1].getWalls() == 3)
      {
        if(maze[i-1].getVisit() == false)
        {
          options.add("W");
        }
      }
    }
    if(options.size() > 0)
    {
      int chooser = rand.nextInt(options.size());
      String choice = options.get(chooser);
      options.clear();
      return choice;
    }
    else
    return "NA";

  }
  //Used For Debuggin
  // public void Print()
  // {
  //   System.out.print("_");
  //   for(int i = 0; i < x*2; i++)
  //   {
  //     System.out.print("_");
  //   }
  //   System.out.println("");
  //   System.out.print("|");
  //   for(int p = 0; p < y*x; p = p + x)
  //   {
  //     for (int i = p+1; i < p+x+1; i++)
  //     {
  //       if(maze[i].getStart() == true)
  //       {
  //         System.out.print("S");
  //       }
  //       if(maze[i].getEnd() == true)
  //       {
  //         System.out.print("E");
  //       }
  //       if(maze[i].getWalls() == 0)
  //       {
  //         System.out.print("_|");
  //       }
  //       if(maze[i].getWalls() == 1)
  //       {
  //         System.out.print("_ ");
  //       }
  //       if(maze[i].getWalls() == 2)
  //       {
  //         System.out.print(" |");
  //       }
  //       if(maze[i].getWalls() == 3)
  //       {
  //         System.out.print("  ");
  //       }
  //     }
  //     System.out.println("");
  //     System.out.print("|");
  //   }
  // }
}
