public class MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i1 = m - 1;
        int i2 = n - 1;
        for(int i = m + n - 1; i >= 0; i --){
            if(nums1[i1] >= nums2[i2]){
                nums1[i] = nums1[i1];
                i1 --;
            }else{
                nums1[i] = nums2[i2];
                i2 --;
            }
            if(i1 < 0 || i2 < 0){
                break;
            }
        }
        if(i2 >= 0){
            System.arraycopy(nums2, 0, nums1, 0, i2 + 1);
        }
    }

}
