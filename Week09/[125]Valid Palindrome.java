//Given a string, determine if it is a palindrome, considering only alphanumeric
// characters and ignoring cases. 
//
// Note: For the purpose of this problem, we define empty string as valid palind
//rome. 
//
// Example 1: 
//
// 
//Input: "A man, a plan, a canal: Panama"
//Output: true
// 
//
// Example 2: 
//
// 
//Input: "race a car"
//Output: false
// 
//
// 
// Constraints: 
//
// 
// s consists only of printable ASCII characters. 
// 
// Related Topics Two Pointers String 
// 👍 1305 👎 3046


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public boolean isPalindrome(String s) {
        if (null == s || s.isEmpty()) return true;
        int i = 0, j = s.length() - 1;
        while (i < j) {
            while (i < j && !isAlphanumeric(s.charAt(i))) i++;
            while (i < j && !isAlphanumeric(s.charAt(j))) j--;
            if (i >= j) return true;
            if (!isEqualIgnoreCase(s.charAt(i++), s.charAt(j--))) return false;
        }
        return true;
    }

    private boolean isAlphanumeric(char a) {
        return (a >= 'A' && a <= 'Z') || (a >= 'a' && a <= 'z') || (a >= '0' && a <= '9');
    }

    private boolean isEqualIgnoreCase(char a, char b) {
        a = (a >= 'A' && a <= 'Z') ? (char) (a + 32) : a;
        b = (b >= 'A' && b <= 'Z') ? (char) (b + 32) : b;
        return a == b;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
