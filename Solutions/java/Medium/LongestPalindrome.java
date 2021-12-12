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

    public String longestPalindrome2(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }
    
    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    public static void main(String[] args) {
        String s = new String("aacabdkacaa");
        String result = longestPalindrome(s);
        System.out.println(result);
    }


}
