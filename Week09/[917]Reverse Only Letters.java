//Given a string S, return the "reversed" string where all characters that are n
//ot a letter stay in the same place, and all letters reverse their positions. 
//
// 
//
// 
// 
// 
// 
// 
// 
// 
// 
//
// 
// Example 1: 
//
// 
//Input: "ab-cd"
//Output: "dc-ba"
// 
//
// 
// Example 2: 
//
// 
//Input: "a-bC-dEf-ghIj"
//Output: "j-Ih-gfE-dCba"
// 
//
// 
// Example 3: 
//
// 
//Input: "Test1ng-Leet=code-Q!"
//Output: "Qedo1ct-eeLg=ntse-T!"
// 
//
// 
//
// 
// Note: 
//
// 
// S.length <= 100 
// 33 <= S[i].ASCIIcode <= 122 
// S doesn't contain \ or " 
// 
// 
// 
// 
// Related Topics String 
// 👍 580 👎 34


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String reverseOnlyLetters(String S) {
        if (null == S || S.isEmpty()) return S;
        char[] chars = S.toCharArray();
        int i = 0, j = S.length() - 1;
        while (i < j) {
            while (i < j && !isAlphabet(S.charAt(i))) i++;
            while (i < j && !isAlphabet(S.charAt(j))) j--;
            if (i >= j) break;
            swap(chars, i++, j--);
        }
        return new String(chars);
    }

    private boolean isAlphabet(char a) {
        return (a >= 'A' && a <= 'Z') || (a >= 'a' && a <= 'z');
    }

    private void swap(char[] array, int i, int j) {
        char tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
