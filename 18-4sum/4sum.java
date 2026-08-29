class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        if (n < 4) return res;

        Arrays.sort(nums);

        for (int w = 0; w < n - 3; w++) {
            if (w > 0 && nums[w] == nums[w - 1]) continue;
            if ((long) nums[w] + nums[w + 1] + nums[w + 2] + nums[w + 3] > target) break;
            if ((long) nums[w] + nums[n - 1] + nums[n - 2] + nums[n - 3] < target) continue;

            for (int x = w + 1; x < n - 2; x++) {
                if (x > w + 1 && nums[x] == nums[x - 1]) continue;
                if ((long) nums[w] + nums[x] + nums[x + 1] + nums[x + 2] > target) break;
                if ((long) nums[w] + nums[x] + nums[n - 1] + nums[n - 2] < target) continue;

                int y = x + 1, z = n - 1;
                while (y < z) {
                    long sum = (long) nums[w] + nums[x] + nums[y] + nums[z];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[w], nums[x], nums[y], nums[z]));
                        y++;
                        z--;
                        while (y < z && nums[y] == nums[y - 1]) y++;
                        while (y < z && nums[z] == nums[z + 1]) z--;
                    } else if (sum < target) {
                        y++;
                    } else {
                        z--;
                    }
                }
            }
        }

        return res;
    }
}