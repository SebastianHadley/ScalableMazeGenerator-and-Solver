//Cell
//Sebastian Hadley
//NxM Depth first search to generate maze
//Create grid of size nxmm random vertex in top left as start. vertex 1
import java.util.*;
public class Cell
{
  //private Variables
  private boolean first;
  private boolean finish;
  private int moves;
  private boolean visited;
  private int cellNo;
  //General Constructor
  public Cell()
  {
    moves = 0;
    visited = false;
    cellNo = 0;
  }
  //Specific Constructor
  public Cell(int walls,int number,boolean begin,boolean end)
  {
    visited = false;
    moves = walls;
    cellNo = number;
    first = begin;
    finish = end;
  }
  //Getters
  public int getWalls()
  {
    return moves;
  }

  public boolean getVisit()
  {
    return visited;
  }
  public int getCellNo()
  {
    return cellNo;
  }
  public boolean getStart()
  {
    return first;
  }
  public boolean getEnd()
  {
    return finish;
  }
  //Setters
  public void setCellNo(int a)
  {
    cellNo = a;
  }
  public void setWalls(int connects)
  {
    moves = connects;
  }
  public void setShut()
  {
    moves = 0;
  }
  public void visit()
  {
    visited = true;
  }
}
