//Given an array nums of n integers, are there elements a, b, c in nums such tha
//t a + b + c = 0? Find all unique triplets in the array which gives the sum of ze
//ro. 
//
// Note: 
//
// The solution set must not contain duplicate triplets. 
//
// Example: 
//
// 
//Given array nums = [-1, 0, 1, 2, -1, -4],
//
//A solution set is:
//[
//  [-1, 0, 1],
//  [-1, -1, 2]
//]
// 
// Related Topics Array Two Pointers


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == nums || nums.length < 3) {
            return result;
        }
        //a+b=-c
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            // 最小的元素都大于0，后面俩元素之和肯定大于0，三者不可能和为0
            if (nums[i] > 0) break;
            // 忽略重复的c，防止结果重复
            if (i > 0 && nums[i] == nums[i-1]) continue;
            int s = i + 1;
            int e = nums.length - 1;
            while (s < e) {
                if (nums[s] + nums[e] + nums[i] > 0) {
                    // 防止a重复
                    while (s < e && nums[e] == nums[--e]);
                } else if (nums[s] + nums[e] + nums[i] < 0) {
                    // 防止b重复
                    while (s < e && nums[s] == nums[++s]);
                } else {
                    result.add(Arrays.asList(nums[i], nums[s], nums[e]));
                    // 防止a、b重复
                    while (s < e && nums[e] == nums[--e]);
                    while (s < e && nums[s] == nums[++s]);
                }
            }
        }

        return result;
    }

    // 利用Set进行结果去重
    public List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == nums || nums.length < 3) {
            return result;
        }
        //a+b=-c
        Arrays.sort(nums);
        Set<List<Integer>> resultSet = new HashSet<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) break;
            int s = i + 1;
            int e = nums.length - 1;
            while (s < e) {
                if (nums[s] + nums[e] + nums[i] > 0) {
                    e--;
                } else if (nums[s] + nums[e] + nums[i] < 0) {
                    s++;
                } else {
                    resultSet.add(Arrays.asList(nums[i], nums[s++], nums[e--]));
                }
            }
        }

        result.addAll(resultSet);
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
