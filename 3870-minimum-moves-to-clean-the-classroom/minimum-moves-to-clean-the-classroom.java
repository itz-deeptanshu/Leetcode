import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        
        int startR = -1, startC = -1;
        int litterCount = 0;
        int[][] litterId = new int[m][n];
        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }
        
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = classroom[r].charAt(c);
                if (ch == 'S') {
                    startR = r;
                    startC = c;
                } else if (ch == 'L') {
                    litterId[r][c] = litterCount++;
                }
            }
        }
        
        // If there's no litter to collect, 0 moves needed.
        if (litterCount == 0) return 0;
        
        int targetMask = (1 << litterCount) - 1;
        
        // maxEnergy[r][c][mask] stores the maximum energy recorded at (r, c) with mask
        int[][][] maxEnergy = new int[m][n][1 << litterCount];
        for (int[][] mat : maxEnergy) {
            for (int[] row : mat) {
                Arrays.fill(row, -1);
            }
        }
        
        // State queue: [row, col, current_energy, mask]
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startR, startC, energy, 0});
        maxEnergy[startR][startC][0] = energy;
        
        int steps = 0;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int r = curr[0];
                int c = curr[1];
                int curEnergy = curr[2];
                int mask = curr[3];
                
                if (mask == targetMask) {
                    return steps;
                }
                
                // If out of energy, student cannot make further moves
                if (curEnergy == 0) continue;
                
                for (int[] d : dirs) {
                    int nr = r + d[0];
                    int nc = c + d[1];
                    
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                    char cell = classroom[nr].charAt(nc);
                    if (cell == 'X') continue;
                    
                    int nextEnergy = curEnergy - 1;
                    int nextMask = mask;
                    
                    // Reset station restores full energy
                    if (cell == 'R') {
                        nextEnergy = energy;
                    } else if (cell == 'L') {
                        nextMask |= (1 << litterId[nr][nc]);
                    }
                    
                    // Only explore if we arrive with strictly greater energy
                    if (nextEnergy > maxEnergy[nr][nc][nextMask]) {
                        maxEnergy[nr][nc][nextMask] = nextEnergy;
                        queue.offer(new int[]{nr, nc, nextEnergy, nextMask});
                    }
                }
            }
            steps++;
        }
        
        return -1;
    }
}