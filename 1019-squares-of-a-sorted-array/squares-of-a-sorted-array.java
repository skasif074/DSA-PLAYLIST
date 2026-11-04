class Solution {
    public int[] sortedSquares(int[] nums) {
        int neg=0;
        int n=nums.length;
        for(int i=0;i<n;i++){
            if(nums[i]<0){
                neg=neg+1;
            }
            nums[i]=nums[i]*nums[i];
        }

        if(neg==0){
            return nums;
        }
        int arr[]=new int[n];
        int k=0;
        int i=neg-1, j=neg;
        while(i>=0&&j<=n-1){
            if(nums[i]<nums[j]){
                arr[k++]=nums[i];
                i--;
            }
            else{
                arr[k++]=nums[j];
                j++;
            }
        }

        while(i>=0){
            arr[k++]=nums[i];
            i--;
        }
        while(j<=n-1){
            arr[k++]=nums[j];
            j++;
        }
        return arr;
    }
}