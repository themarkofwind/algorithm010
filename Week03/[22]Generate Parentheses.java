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

    private List<String> result;

    public List<String> generateParenthesis(int n) {
        result = new ArrayList<>();
        if (n > 0) {
            generate(0, 0, n, "");
        }
        return result;
    }

    /**
     * 递归生成左右括号拼接
     * @param left 左括号个数
     * @param right 右括号个数
     * @param max 左/右括号总数
     * @param str 当前生成的str
     */
    private void generate(int left, int right, int max, String str) {
        // terminator
        if (left == max && right == max) {
            result.add(str);
            return;
        }
        // process current && drill down
        if (left < max) {
            generate(left + 1, right, max, str + "(");
        }
        if (right < left) {
            generate(left, right + 1, max, str + ")");
        }

        // reverse
    }


    public List<String> generateParenthesis1(int n) {
        result = new ArrayList<>();
        if (n > 0) {
            generate(0, 2 * n, "");
        }
        return result;
    }

    // 不考虑左右括号合法的情况
    private void generate(int level, int max, String str) {
        // terminator
        if (level >= max) {
            result.add(str);
            return;
        }

        // process current
        String s1 = str + "(";
        String s2 = str + ")";

        // drill down
        generate(level + 1, max, s1);
        generate(level + 1, max, s2);

        // reverse
    }
}
//leetcode submit region end(Prohibit modification and deletion)
