/*
Code
Code Sample
Code Sample
Accepted
Accepted
Testcase
Testcase
Test Result
1. Two Sum
Solved
Easy
Topics
premium lock icon
Companies
Hint
Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.

 

Example 1:

Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
Example 2:

Input: nums = [3,2,4], target = 6
Output: [1,2]
Example 3:

Input: nums = [3,3], target = 6
Output: [0,1]
 

Constraints:

2 <= nums.length <= 104
-109 <= nums[i] <= 109
-109 <= target <= 109
Only one valid answer exists.
 

Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?*/


class Solution {
    public int[] twoSum(int[] nums, int target) {
        int res[]=new int[2];
        for(int i=0;i<nums.length;i++){
            for(int j=0;j<nums.length;j++){
                if(i!=j && nums[i]+nums[j]==target){
                    res[0]=i;
                    res[1]=j;
                }
            }
        }
        return res;
    }
}


class Solution1 {
    public int[] twoSum(int[] nums, int target) {
        int arr[]=new int[2];
        Map <Integer,Integer> set = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int com=target-nums[i];
            if(set.containsKey(com)){
                arr[0]=i;
                arr[1]=set.get(com);
                return arr;

            }
            set.put(nums[i],i);
        }
        return arr;

    }
}


class Solution3 {
    public int[] twoSum(int[] nums, int target) {

        Map <Integer,Integer> set = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int com=target-nums[i];
            if(set.containsKey(com)){
                return new int[]{i,set.get(com)};

            }
            set.put(nums[i],i);
        }
        return new int[]{};

    }
}


