public class TrappingRainWater {

    /**
     * 通过从底部一层一层向上构造立方体，求出总面积，减去每一个柱状的面积，就是雨水的面积；
     * 利用双指针，分别从最左、最右向中间前进，如果左边或者右边出现新的极大值，则计算当前的极大值和之前的极
     * 大值构成的面积，累加进结果，同时减去每一个柱子的高度
     * @param height
     * @return
     */
    public int trap(int[] height){
        if(height.length == 0){
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int lastHeight = 0;
        int result = 0;
        int leftMax = 0;
        int rightMax = 0;
        while(left <= right){
            if(height[left] < height[right]){
                if(height[left] > leftMax){
                    int minHeight = Math.min(height[left], height[right]);
                    result += (minHeight - lastHeight) * (right - left + 1);
                    leftMax = height[left];
                    lastHeight = minHeight;
                }
                result -= height[left];
                left ++;
            }else{
                if(height[right] > rightMax){
                    int minHeight = Math.min(height[left], height[right]);
                    result += (minHeight - lastHeight) * (right - left + 1);
                    rightMax = height[right];
                    lastHeight = minHeight;
                }
                result -= height[right];
                right --;
            }
        }
        return result;
    }

}
