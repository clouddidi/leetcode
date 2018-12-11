import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

@TagAnnotation(tags={"DFS"})
public class Lyft_MostValuableIsland {
  public static void main(String[] args) {
    int[][] grid = new int[][] {
      {0, 0, 0, 0, 0},
      {0, 2, 4, 0, 0},
      {0, 1, 1, 0, 0},
      {0, 0, 0, 4, 0},
      {0, 3, 0, 0, 0}
    };
    Lyft_MostValuableIsland sol = new Lyft_MostValuableIsland();
    sol.getMostValuable(grid);
  }
  
  public int getMostValuable(int[][] grid) {
    if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
      return 0; 
    }
    
    int max = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] > 0) {
          max = Math.max(max, maxValue(grid, i, j));
        }
      }
    }
    System.out.println(max);
    return max;
  }

  public int maxValue(int[][] grid, int i, int j) {
    if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == -1) {
      return 0; 
    }
    
    int sum = 0;
    if (grid[i][j] > 0) {
      int old = grid[i][j];
      grid[i][j] = -1;
      int[] dx = {0, 0,  1, -1};
      int[] dy = {1, -1, 0, 0};
      for (int k = 0; k < 4; k++) {
        sum += maxValue(grid, i + dx[k], j + dy[k]);
      }
      sum += old;
    }
    
    return sum;
  }
}

/* 
Your previous Plain Text content is preserved below:

This is just a simple shared plaintext pad, with no execution capabilities.

When you know what language you'd like to use for your interview,
simply choose it from the dropdown in the top bar.

You can also change the default language your pads are created with
in your account settings: https://coderpad.io/settings

Enjoy your interview!


0 0 0 0 0
0 2 4 0 0
0 1 1 0 0
0 0 0 4 0
0 3 0 0 0

=> 8

def most_valuable_island(world):
  ...
  

 */