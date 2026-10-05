class Solution {
    public int[] runningSum(int[] nums) {
        int cursum=0;
        int l=nums.length;
        int ans[]=new int[l];
        for(int i=0;i<l;i++){
            ans[i]=cursum+nums[i];
            cursum=ans[i];
        }
        return ans;
    }
}