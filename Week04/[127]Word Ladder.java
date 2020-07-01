//Given two words (beginWord and endWord), and a dictionary's word list, find th
//e length of shortest transformation sequence from beginWord to endWord, such tha
//t: 
//
// 
// Only one letter can be changed at a time. 
// Each transformed word must exist in the word list. 
// 
//
// Note: 
//
// 
// Return 0 if there is no such transformation sequence. 
// All words have the same length. 
// All words contain only lowercase alphabetic characters. 
// You may assume no duplicates in the word list. 
// You may assume beginWord and endWord are non-empty and are not the same. 
// 
//
// Example 1: 
//
// 
//Input:
//beginWord = "hit",
//endWord = "cog",
//wordList = ["hot","dot","dog","lot","log","cog"]
//
//Output: 5
//
//Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog
//" -> "cog",
//return its length 5.
// 
//
// Example 2: 
//
// 
//Input:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//Output: 0
//
//Explanation: The endWord "cog" is not in wordList, therefore no possible trans
//formation.
// 
//
// 
// 
// Related Topics Breadth-first Search


import java.util.HashSet;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // bfs
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0;
        wordSet.remove(beginWord);

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        LinkedList<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        int result = 1;
        int len = beginWord.length();
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String word = queue.poll();
                char[] chars = word.toCharArray();
                // 不同位置替换一个字母
                for (int i = 0; i < len; i++) {
                    char origin = chars[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[i] = c;
                        String next = new String(chars);
                        // 判断修改一位后的单词是否在字典里
                        if (wordSet.contains(next)) {
                            // 下个结点是否为最终结点
                            if (next.equals(endWord)) return result + 1;
                            // 记录相邻层结点
                            if (!visited.contains(next)) {
                                queue.offer(next);
                                visited.add(next);
                            }
                        }
                    }
                    // 恢复第i位至初始状态
                    chars[i] = origin;
                }
            }
            // 路径长度即为层数
            result++;
        }
        // 没有遇到最终结点
        return 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
