/*
Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
Note: You can only put the bomb at an empty cell.
*/
@TagAnnotation(tags={"Other"})
class Solution {
    private boolean check(char[][] grid, int x, int y) {
        int row = grid.length;
        int col = grid[0].length;
        if (x >= 0 && x < row && y >= 0 && y < col)
            return true;
        return false;
    }

    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        
        int[] dx = new int[] {0, 0, -1, 1};
        int[] dy = new int[] {-1, 1, 0, 0};
        int row = grid.length;
        int col = grid[0].length;
        int[][] score = new int[row][col];
        int max = 0;
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 'E') {
                    for (int k = 0; k < 4; k++) {
                        int x = i, y = j;
                        while (check(grid, x, y) && grid[x][y] != 'W') {
                            if (grid[x][y] == '0') {
                                score[x][y] += 1;
                                if (score[x][y] > max)
                                    max = score[x][y];
                            }
                            x += dx[k];
                            y += dy[k];
                        }
                    }
                }
            }

        return max;
    }

    /*
    // bad way to solve the problem
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0)
            return 0;

        int row = grid.length;
        int col = grid[0].length;
        int[][] score = new int[row][col];
        for (int i = 0; i < row; i++) {
            List<Integer> pos = new ArrayList<>();
            List<Integer> value = new ArrayList<>();
            int sum = 0;
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 'E')
                    sum++;
                if (grid[i][j] == 'W') {
                    pos.add(j);
                    value.add(sum);
                    sum = 0;
                }
            }
            pos.add(col);
            value.add(sum);
            int index = 0;
            int p = pos.get(index);
            int v = value.get(index);
            for (int k = 0; k < pos.size(); k++)
                System.out.print(pos.get(k) + "-" + value.get(k) + ",");
            System.out.println();
            for (int j = 0; j < col; j++) {
                if (j > p) {
                    index++;
                    p = pos.get(index);
                    v = value.get(index);
                }
                if (grid[i][j] == '0' && j <= p)
                    score[i][j] = v;
            }   
        }

        for (int j = 0; j < col; j++) {
            List<Integer> pos = new ArrayList<>();
            List<Integer> value = new ArrayList<>();
            int sum = 0;
            for (int i = 0; i < row; i++) {
                if (grid[i][j] == 'E')
                    sum++;
                if (grid[i][j] == 'W') {
                    pos.add(i);
                    value.add(sum);
                    sum = 0;
                }
            }
            pos.add(row);
            value.add(sum);
            int index = 0;
            int p = pos.get(index);
            int v = value.get(index);
            for (int k = 0; k < pos.size(); k++)
                System.out.print(pos.get(k) + "-" + value.get(k) + ",");
            System.out.println();
            for (int i = 0; i < row; i++) {
                if (i > p) {
                    index++;
                    p = pos.get(index);
                    v = value.get(index);
                }
                if (grid[i][j] == '0' && j <= p)
                    score[i][j] = score[i][j] + v;
            }   
        }

        int max = 0;
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) {
                if (score[i][j] > max) {
                    max = score[i][j];   
                    System.out.println(i + "," + j);
                }
            }
        return max;
    }
    */
}