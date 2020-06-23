//Implement pow(x, n), which calculates x raised to the power n (xn). 
//
// Example 1: 
//
// 
//Input: 2.00000, 10
//Output: 1024.00000
// 
//
// Example 2: 
//
// 
//Input: 2.10000, 3
//Output: 9.26100
// 
//
// Example 3: 
//
// 
//Input: 2.00000, -2
//Output: 0.25000
//Explanation: 2-2 = 1/22 = 1/4 = 0.25
// 
//
// Note: 
//
// 
// -100.0 < x < 100.0 
// n is a 32-bit signed integer, within the range [−231, 231 − 1] 
// 
// Related Topics Math Binary Search


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // x * x * ... x (n)
    // (x * ... x)(n/2) * (x * ... x)(n/2) * (n % 2 == 0 ? 1 : n)
    // n < 0 -> 1/x * 1/x ... 1/x (-n)
    public double myPow(double x, int n) {
        long nLong = n;
        return pow(x, nLong);
    }

    private double pow(double x, long n) {
        if (n == 0) return 1;
        // -n Integer.MAX_VALUE取负值会越界
        if (n < 0) return pow(1/x, -n);
        if (n == 1) {
            return x;
        } else {
            double temp = pow(x, n / 2);
            if (n % 2 == 0) {
                return temp * temp;
            } else {
                return temp * temp * x;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
