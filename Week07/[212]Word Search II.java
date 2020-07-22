//Given a 2D board and a list of words from the dictionary, find all words in th
//e board. 
//
// Each word must be constructed from letters of sequentially adjacent cell, whe
//re "adjacent" cells are those horizontally or vertically neighboring. The same l
//etter cell may not be used more than once in a word. 
//
// 
//
// Example: 
//
// 
//Input: 
//board = [
//  ['o','a','a','n'],
//  ['e','t','a','e'],
//  ['i','h','k','r'],
//  ['i','f','l','v']
//]
//words = ["oath","pea","eat","rain"]
//
//Output: ["eat","oath"]
// 
//
// 
//
// Note: 
//
// 
// All inputs are consist of lowercase letters a-z. 
// The values of words are distinct. 
// 
// Related Topics Backtracking Trie 
// 👍 2584 👎 112


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    class Trie {

        private Trie[] next;
        private boolean isEnd;

        public Trie() {
            next = new Trie[26];
            isEnd = false;
        }

        public void insert(String word) {
            if (null == word || word.length() <= 0) return;
            Trie root = this;
            for (char ch : word.toCharArray()) {
                if (root.next[ch - 'a'] == null) {
                    root.next[ch - 'a'] = new Trie();
                }
                root = root.next[ch - 'a'];
            }
            root.isEnd = true;
        }

        public Trie search(char ch) {
            return (null != this.next[ch - 'a']) ? this.next[ch - 'a'] : null;
        }
    }

    // 结果去重
    Set<String> result = new HashSet<>();

    public List<String> findWords(char[][] board, String[] words) {
        if (null == words || words.length <= 0) return new ArrayList<>();
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        // 每个位置都要作为起点dfs搜索一下
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                dfs(trie, board, i, j, "");
            }
        }
        return new ArrayList<>(result);
    }

    private void dfs(Trie trie, char[][] board, int i, int j, String str) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length || board[i][j] == '.') {
            return;
        }
        // 防止一个位置字母被重复使用
        char temp = board[i][j];
        board[i][j] = '.';
        Trie node = trie.search(temp);
        if (null != node) {
            str += temp;
            if (node.isEnd) {
                result.add(str);
            }
            // 上面即使找到单词，也需要继续dfs搜索
            // 否则可能会漏掉解，(e.g. 'aaa', 'aaab')
            dfs(node, board, i, j + 1, str);
            dfs(node, board, i, j - 1, str);
            dfs(node, board, i + 1, j, str);
            dfs(node, board, i - 1, j, str);
        }
        board[i][j] = temp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
