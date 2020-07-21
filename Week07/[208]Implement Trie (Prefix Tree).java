//Implement a trie with insert, search, and startsWith methods. 
//
// Example: 
//
// 
//Trie trie = new Trie();
//
//trie.insert("apple");
//trie.search("apple");   // returns true
//trie.search("app");     // returns false
//trie.startsWith("app"); // returns true
//trie.insert("app");   
//trie.search("app");     // returns true
// 
//
// Note: 
//
// 
// You may assume that all inputs are consist of lowercase letters a-z. 
// All inputs are guaranteed to be non-empty strings. 
// 
// Related Topics Design Trie 
// 👍 3225 👎 50


//leetcode submit region begin(Prohibit modification and deletion)
class Trie {

    private Trie[] next;
    private boolean isWord;

    /** Initialize your data structure here. */
    public Trie() {
        next = new Trie[26];
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (null == word || word.length() <= 0) return;
        Trie root = this;
        for (char ch : word.toCharArray()) {
            if (null == root.next[ch - 'a']) {
                root.next[ch - 'a'] = new Trie();
            }
            root = root.next[ch - 'a'];
        }
        root.isWord = true;
    }

    // optimize
    public boolean search(String word) {
        Trie trie = searchPrefix(word);
        return null != trie && trie.isWord;
    }

    // optimze
    public boolean startsWith(String prefix) {
        Trie trie = searchPrefix(prefix);
        return null != trie;
    }

    private Trie searchPrefix(String prefix) {
        if (null == prefix || prefix.length() <= 0) return null;
        Trie root = this;
        for (char ch : prefix.toCharArray()) {
            if (null == root.next[ch - 'a']) {
                return null;
            } else {
                root = root.next[ch - 'a'];
            }
        }
        return root;
    }

    /** Returns if the word is in the trie. */
    public boolean search1(String word) {
        if (null == word || word.length() <= 0) return false;
        Trie root = this;
        for (char ch : word.toCharArray()) {
            if (null == root.next[ch - 'a']) {
                return false;
            } else {
                root = root.next[ch - 'a'];
            }
        }
        return root.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith1(String prefix) {
        if (null == prefix || prefix.length() <= 0) return false;
        Trie root = this;
        for (char ch : prefix.toCharArray()) {
            if (null == root.next[ch - 'a']) {
                return false;
            } else {
                root = root.next[ch - 'a'];
            }
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)
