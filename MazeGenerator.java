//MazeGenerator
//Sebastian Hadley
//NxM Depth first search to generate maze
//Create grid of size nxmm random vertex in top left as start.
//Was made using random cell selection as i had finished before the specification was uppdated.
import java.util.*;
import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
class MazeGenerator
{

  public static void main(String[] args)
  {
    int n = Integer.parseInt(args[0]);
    int m = Integer.parseInt(args[1]);
    String file_name = args[2];
    MazeGenerator mg = new MazeGenerator();
    mg.run(n,m,file_name);
  }
  //Runs the program
  private void run(int x,int y,String output)
  {
    int n = x;
    int m = y;
    Maze newMaze = new Maze(n,m);
    String temp_string = newMaze.returnMaze();
    CreateFile(temp_string,output);
  }
  //outputs to .date file
  public void CreateFile(String out,String file_save)
  {
    try
    {
      File output = new File(file_save);
    }
    catch(Exception E)
    {}
    try
    {
      FileWriter myWriter = new FileWriter(file_save);
      myWriter.write(out);
      myWriter.close();
    }
    catch(Exception E)
    {}
    }
  }
