public class ContainerWithMostWater {
    public static int maxArea(int[] height) {
        int maxarea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r]){ 
                l++;
            }
            else {
                r--;
            }
        }
        return maxarea;
    }
    

    public static void main(String[] args) {
        int[] heights = {1,2,3,4,5,3};
        int result = maxArea(heights);
        System.out.println(result);
    }
}
