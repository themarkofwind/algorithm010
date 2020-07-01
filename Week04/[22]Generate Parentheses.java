//
//Given n pairs of parentheses, write a function to generate all combinations of
// well-formed parentheses.
// 
//
// 
//For example, given n = 3, a solution set is:
// 
// 
//[
//  "((()))",
//  "(()())",
//  "(())()",
//  "()(())",
//  "()()()"
//]
// Related Topics String Backtracking


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        dfs(result, "", n, 0, 0);
        return result;
    }

    private void dfs(List<String> result, String s, int n, int left, int right) {
        if (left == n && right == n) {
            result.add(s);
            return;
        }
        if (left < n) dfs(result, s + "(", n, left + 1, right);
        if (right < left) dfs(result, s + ")", n, left, right + 1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
