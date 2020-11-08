public class RotateArray {

    public void rotate(int[] nums, int k){
        k = k % nums.length;
        if(k == 0){
            return;
        }
        int count = 0;
        for(int start = 0; count < nums.length; start++){
            int current = start;
            int prev = nums[start];
            do{
                int next = (current+ k ) % nums.length;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count ++;
            }while(start != current);
        }
    }

}
