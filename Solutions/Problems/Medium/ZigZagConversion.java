public class ZigZagConversion {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuilder result = new StringBuilder();
        int limit = numRows - 1;
        int index = 0;
        for (int i = 0; i < Math.min(numRows, s.length()); i++) {
            index = i;
            int previous = index;
            result.append(s.charAt(index));
            Boolean isEven = true;
            while (index <= s.length()) {
                previous = index;
                if (isEven) {
                    index += 2 * (limit - i);
                    isEven = false;
                } else {
                    index += 2 * i;
                    isEven = true;
                }
                if ((index != previous) && (index < s.length())) {
                    result.append(s.charAt(index));
                }
            }
        }
        return result.toString(); 
    }
    
    public static void main(String[] args) {
        String s = new String("PAYPALISHIRING");
        int numRows = 1;
        String result = convert(s, numRows);
        System.out.println(result);
    }
}
