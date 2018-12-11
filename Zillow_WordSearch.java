import java.io.*;
import java.util.*;

// T A M 
// E I N 
// S T G 

// "TEST" - true 
// + any direction
// + item can only be used once
// "TESTS" - false 

@TagAnnotation(tags={"DFS"})
public class Zillow_WordSearch {
  public static void main(String[] args) {
    char[][] grid = new char[][] {
      {'t', 'a', 'm'},
      {'e', 'i', 'n'},
      {'s', 't', 'g'}
    };
    Zillow_WordSearch sol = new Zillow_WordSearch();
    System.out.println(sol.checkBoard(grid, "mat"));
  }

    private boolean checkBoundary(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }
        return true;
    }

    public boolean find(char[][] board, int i, int j, String word, int start, StringBuilder sb) {
        if (start == word.length()) {
            return true;
        }
        
        if (checkBoundary(board, i, j))
            System.out.println(i + "," + j + "," + sb);

        if (!checkBoundary(board, i, j) || board[i][j] != word.charAt(start) || board[i][j] == '0') {
            return false;
        }
        
        char temp = board[i][j];
        board[i][j] = '0';
        sb.append(temp);
        boolean result = find(board, i, j + 1, word, start + 1, sb)
        || find(board, i, j - 1, word, start + 1, sb)
        || find(board, i + 1, j, word, start + 1, sb)
        || find(board, i - 1, j, word, start + 1, sb);
        sb.deleteCharAt(sb.length() - 1);
        board[i][i] = temp;
        
        return result;
    }

    public boolean checkBoard(char[][] board, String word) {
        if (board == null || board.length == 0) {
            return false;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                StringBuilder sb = new StringBuilder();
                if (find(board, i, j, word, 0, sb)) {
                    return true;
                }
            }
        }
        return false;   
    }
}

