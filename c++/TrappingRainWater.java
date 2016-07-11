/**
 * Created on 7/10/16.
 * Given n non-negative integers representing
 * an elevation map where the width of each
 * bar is 1, compute how much water it is
 * able to trap after raining.
 *
 * For example,
 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 */

public class TrappingRainWater {

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        // int[] height = {1,0,2,1};
        TrappingRainWater app = new TrappingRainWater();
        System.out.println(app.trap(height));
    }

    public int trap(int[] height) {
        if (null == height || height.length < 3) return 0;
        int water = 0;
        int maxHeight = 0;
        for (int i = 0; i < height.length; ++i) {
            if (height[i] < maxHeight) {
                water += maxHeight - height[i];
            }
            maxHeight = Math.max(height[i], maxHeight);
        }

        int preMaxHeight = maxHeight;
        maxHeight = 0;
        for (int i = height.length - 1; i >= 0; --i) {
            maxHeight = Math.max(height[i], maxHeight);
            if (maxHeight < preMaxHeight) {
                water -= preMaxHeight - maxHeight;
            }
        }

        return water;
    }
}
