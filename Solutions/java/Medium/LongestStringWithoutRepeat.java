import java.util.HashMap;

public class LongestStringWithoutRepeat {
    public static int lengthOfLongestSubstring(String s) {
        int maxLength = -1;
        int head = 0;
        int newHead = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                newHead = map.get(s.charAt(i)) + 1;
                head = (head >= newHead) ? head : newHead;
                map.put(s.charAt(i), i);
            }
            maxLength = (maxLength >= (i - head)) ? maxLength : (i - head);
            map.put(s.charAt(i), i);
        }
        return maxLength + 1;
    }

    public int lengthOfLongestSubstring1(String s) {
        int n = s.length(), ans = 0;
        HashMap<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }
    
    public static void main(String[] args) {
        String s = "qerreed";
        int result = lengthOfLongestSubstring(s);
        System.out.println(result);
    }
}
