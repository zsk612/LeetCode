public class LongestHappyPrefix {
    public static String longestPrefix(String s) {
        if (s == null || s.length() <= 1) {
            return "";
        }
        int n = s.length();
        int[] lps = buildLPS(s);
        int len = lps[n-1];
        return s.substring(0, len);
    }
    
    private static int[] buildLPS (String s) {
        char[] sc = s.toCharArray();
        int n = sc.length;
        int[] lps = new int[n];
        lps[0] = 0;
        int i = 1;
        int len = 0;
        while (i < n) {
            if (sc[i] == sc[len]) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len > 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = len;
                    i++;
                }
            }
        }
        return lps;
    }

    public static void main(String[] args) {
        String target = "babbb";
        System.out.println(longestPrefix(target));
    }
}
