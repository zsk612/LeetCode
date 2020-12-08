public class LongestPalindrome {
    public static String longestPalindrome(String s) {
        int maxLen = 0;
        int minStartIndex = 0;
        int maxEndIndex = 0;
        for (int startIndex = 0; startIndex < s.length(); startIndex++) {
            int i = startIndex;
            int j = s.length() - 1;
            int endIndex = j;
            while (j >= i) {
                if (s.charAt(j) == s.charAt(i)) {
                    j--;
                    i++;
                } else {
                    endIndex--;
                    j = endIndex;
                    i = startIndex;
                }
            }
            if (maxLen < (endIndex - startIndex)) {
                minStartIndex = startIndex;
                maxEndIndex = endIndex;
                maxLen = endIndex - startIndex;
            }
        }
        return s.substring(minStartIndex, maxEndIndex + 1);
    }

    public static void main(String[] args) {
        String s = new String("aacabdkacaa");
        String result = longestPalindrome(s);
        System.out.println(result);
    }


}
