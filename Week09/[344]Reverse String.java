//Write a function that reverses a string. The input string is given as an array
// of characters char[]. 
//
// Do not allocate extra space for another array, you must do this by modifying 
//the input array in-place with O(1) extra memory. 
//
// You may assume all the characters consist of printable ascii characters. 
//
// 
//
// 
// Example 1: 
//
// 
//Input: ["h","e","l","l","o"]
//Output: ["o","l","l","e","h"]
// 
//
// 
// Example 2: 
//
// 
//Input: ["H","a","n","n","a","h"]
//Output: ["h","a","n","n","a","H"]
// 
// 
// 
// Related Topics Two Pointers String 
// 👍 1508 👎 704


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void reverseString(char[] s) {
        if (null == s || s.length <= 0) return;
        int i = 0, j = s.length - 1;
        while (i < j) swap(s, i++, j--);
    }

    private void swap(char[] array, int i, int j) {
        char tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
