public class BestTimetoBuyAndSell {
    public int maxProfit(int K, int[] prices) {
        int n = prices.length;
       if (K > n/2) {
           K = n/2;
       }
       int[] dp_ik0 = new int[K + 1];
       int[] dp_ik1 = new int[K + 1];
       Arrays.fill(dp_ik1, Integer.MIN_VALUE);

       for (int i = 0; i < n; i++) {
           for (int k = 1; k <= K; k++) {
               dp_ik0[k] = Math.max(dp_ik0[k], dp_ik1[k] + prices[i]);
               dp_ik1[k] = Math.max(dp_ik1[k], dp_ik0[k - 1] - prices[i]);
           }
       }

       return dp_ik0[K];
   }
}
