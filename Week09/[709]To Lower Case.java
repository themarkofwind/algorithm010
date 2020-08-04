//Implement function ToLowerCase() that has a string parameter str, and returns 
//the same string in lowercase. 
//
// 
//
// 
// Example 1: 
//
// 
//Input: "Hello"
//Output: "hello"
// 
//
// 
// Example 2: 
//
// 
//Input: "here"
//Output: "here"
// 
//
// 
// Example 3: 
//
// 
//Input: "LOVELY"
//Output: "lovely"
// 
// 
// 
// Related Topics String 
// 👍 494 👎 1577


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 'a' - ascii 97
    // 'A' - ascii 65
    public String toLowerCase(String str) {
        if (null == str || str.isEmpty()) return str;
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 'A' && chars[i] <= 'Z') {
                chars[i] += 'a' - 'A';
            }
        }
        return new String(chars);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
