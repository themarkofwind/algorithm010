//
//Given a string and an integer k, you need to reverse the first k characters fo
//r every 2k characters counting from the start of the string. If there are less t
//han k characters left, reverse all of them. If there are less than 2k but greate
//r than or equal to k characters, then reverse the first k characters and left th
//e other as original.
// 
//
// Example: 
// 
//Input: s = "abcdefg", k = 2
//Output: "bacdfeg"
// 
// 
//
//Restrictions: 
// 
// The string consists of lower English letters only. 
// Length of the given string and k will in the range [1, 10000] 
// Related Topics String 
// 👍 440 👎 1249


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String reverseStr(String s, int k) {
        if (null == s || s.length() <= 1) return s;
        char[] chars = s.toCharArray();
        int len = s.length();
        int n = len / (2 * k);
        int m = len % (2 * k);
        int b;
        int e;
        for (int i = 0; i < n; i++) {
            b = i * k * 2;
            e = b + k - 1;
            while (b < e) swap(chars, b++, e--);
        }
        b = n * k * 2;
        e = Math.min(b + k - 1, len - 1);
        while (b < e) swap(chars, b++, e--);
        return new String(chars);
    }

    private void swap(char[] array, int i, int j) {
        char tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
