public class RegExMatch {
    public static boolean isMatch(String s, String p) {
        return isMatchUtil(s, p, s.length() - 1, p.length() - 1);
    }

    private static boolean isMatchUtil(String s, String p, int i, int j) {
        if (i == -1 && j == -1) {
            return true;
        }

        if (j < 0) {
            return false;
        }

        if (i == -1) {
            if (p.charAt(j) == '*') {
                return isMatchUtil(s, p, i, j - 2);
            }
            return false;
        }

        if (s.charAt(i) == p.charAt(j) || (p.charAt(j) == '.')) {
            return isMatchUtil(s, p, i - 1, j - 1);
        }
        if (p.charAt(j) == '*' && j > 0) {
            if (s.charAt(i) == p.charAt(j - 1) || (p.charAt(j - 1) == '.')) {
                return isMatchUtil(s, p, i - 1, j) || isMatchUtil(s, p, i, j - 2);
            } else {
                return isMatchUtil(s, p, i, j - 2);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        String sentence = "aaa";
        String pattern = "a*";
        System.out.println(isMatch(sentence, pattern));
    }
}
