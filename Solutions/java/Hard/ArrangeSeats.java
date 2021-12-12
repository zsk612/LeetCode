public class ArrangeSeats {

    /**
     * 
     * Side notes for bitmasking operations:
     * 1.  Use (x >> i) & 1 to get i-th bit in state x 
     *      or use x & (1 << i) to check i-th bit in if condition
     * 2.  Use (x & y) == x to check if x is a subset of y, 
     *      i.e. every state in x could be 1 only if the corresponding state in y is 1
     * 3.  Use (x & (x >> 1)) == 0 to check if there are no adjancent valid states in x.        
     */



    public int maxStudents(char[][] seats) {
        int m = seats.length, n = seats[0].length;
        int[] validRows = new int[m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                validRows[i] = (validRows[i] << 1) + (seats[i][j] == '.' ? 1 : 0);
        int stateSize = 1 << n; // There are 2^n states for n columns in binary format
        int[][] dp = new int[m][stateSize];
        for (int i = 0; i < m; i++) Arrays.fill(dp[i], -1);
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < stateSize; j++) {
		        // (j & valid) == j: check if j is a subset of valid
		        // !(j & (j >> 1)): check if there is no adjancent students in the row
                if (((j & validRows[i]) == j) && ((j & (j >> 1)) == 0)) {
                    if (i == 0) {
                        dp[i][j] = Integer.bitCount(j);
                    } else {
                        for (int k = 0; k < stateSize; k++) {
                            // !(j & (k >> 1)): no students in the upper left positions
						    // !((j >> 1) & k): no students in the upper right positions
						    // dp[i-1][k] != -1: the previous state is valid
                            if ((j & (k >> 1)) == 0 && ((j >> 1) & k) == 0 && dp[i-1][k] != -1)  {
                                dp[i][j] = Math.max(dp[i][j], dp[i-1][k] + Integer.bitCount(j));
                            }
                        }
                    }
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }
        return ans;
    }
}
