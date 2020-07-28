//Given a non negative integer number num. For every numbers i in the range 0 ≤ 
//i ≤ num calculate the number of 1's in their binary representation and return th
//em as an array. 
//
// Example 1: 
//
// 
//Input: 2
//Output: [0,1,1] 
//
// Example 2: 
//
// 
//Input: 5
//Output: [0,1,1,2,1,2]
// 
//
// Follow up: 
//
// 
// It is very easy to come up with a solution with run time O(n*sizeof(integer))
//. But can you do it in linear time O(n) /possibly in a single pass? 
// Space complexity should be O(n). 
// Can you do it like a boss? Do it without using any builtin function like __bu
//iltin_popcount in c++ or in any other language. 
// Related Topics Dynamic Programming Bit Manipulation 
// 👍 2766 👎 171


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // dp2: dp[n] = dp[n & (n - 1)] + 1
    // (n & (n - 1))将最低位1置为0
    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            result[i] = result[i & (i - 1)] + 1;
        }
        return result;
    }

    // dp1: dp[n] = dp[n/2] + (n & 1)
    // n/2的1个数 + n末位
    // 10010101
    //  1001010
    public int[] countBits2(int num) {
        int[] result = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            result[i] = result[i / 2] + (i & 1);
        }
        return result;
    }

    public int[] countBits1(int num) {
        int[] result = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            result[i] = countOne(i);
        }
        return result;
    }

    private int countOne(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
