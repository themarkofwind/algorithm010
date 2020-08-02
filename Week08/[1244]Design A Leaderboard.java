//Design a Leaderboard class, which has 3 functions: 
//
// 
// addScore(playerId, score): Update the leaderboard by adding score to the give
//n player's score. If there is no player with such id in the leaderboard, add him
// to the leaderboard with the given score. 
// top(K): Return the score sum of the top K players. 
// reset(playerId): Reset the score of the player with the given id to 0 (in oth
//er words erase it from the leaderboard). It is guaranteed that the player was ad
//ded to the leaderboard before calling this function. 
// 
//
// Initially, the leaderboard is empty. 
//
// 
// Example 1: 
//
// 
//Input: 
//["Leaderboard","addScore","addScore","addScore","addScore","addScore","top","r
//eset","reset","addScore","top"]
//[[],[1,73],[2,56],[3,39],[4,51],[5,4],[1],[1],[2],[2,51],[3]]
//Output: 
//[null,null,null,null,null,null,73,null,null,null,141]
//
//Explanation: 
//Leaderboard leaderboard = new Leaderboard ();
//leaderboard.addScore(1,73);   // leaderboard = [[1,73]];
//leaderboard.addScore(2,56);   // leaderboard = [[1,73],[2,56]];
//leaderboard.addScore(3,39);   // leaderboard = [[1,73],[2,56],[3,39]];
//leaderboard.addScore(4,51);   // leaderboard = [[1,73],[2,56],[3,39],[4,51]];
//leaderboard.addScore(5,4);    // leaderboard = [[1,73],[2,56],[3,39],[4,51],[5
//,4]];
//leaderboard.top(1);           // returns 73;
//leaderboard.reset(1);         // leaderboard = [[2,56],[3,39],[4,51],[5,4]];
//leaderboard.reset(2);         // leaderboard = [[3,39],[4,51],[5,4]];
//leaderboard.addScore(2,51);   // leaderboard = [[2,51],[3,39],[4,51],[5,4]];
//leaderboard.top(3);           // returns 141 = 51 + 51 + 39;
// 
//
// 
// Constraints: 
//
// 
// 1 <= playerId, K <= 10000 
// It's guaranteed that K is less than or equal to the current number of players
//. 
// 1 <= score <= 100 
// There will be at most 1000 function calls. 
// 
// Related Topics Hash Table Sort Design 
// 👍 94 👎 49


import java.util.ArrayList;
import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Leaderboard {

    final static int MAX_SCORES = 1000;

    Map<Integer, Integer> playerScoreMap;
    List<Integer>[] board;

    public Leaderboard() {
        playerScoreMap = new HashMap<>();
        board = new List[MAX_SCORES + 1];
    }
    
    public void addScore(int playerId, int score) {
        Integer oldScore = playerScoreMap.get(playerId);
        // clear player's score if exists
        if (null != oldScore) {
            clearPlayer(playerId, board[oldScore]);
            score += oldScore;
        }
        playerScoreMap.put(playerId, score);
        // add player use counting sort
        List<Integer> players = board[score];
        if (null == players) {
            players = new ArrayList<>();
            board[score] = players;
        }
        players.add(playerId);
    }

    private void clearPlayer(int playerId, List<Integer> players) {
        if (null == players) return;
        playerScoreMap.remove(playerId);
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).intValue() == playerId) {
                players.remove(i);
                return;
            }
        }
    }
    
    public int top(int K) {
        if (K <= 0) return 0;
        int sum = 0;
        for (int i = MAX_SCORES; i >= 1 && K > 0; i--) {
            List<Integer> players = board[i];
            if (null != players) {
                int size = players.size();
                while (size-- > 0 && K > 0) {
                    sum += i;
                    K--;
                }
            }
        }
        return sum;
    }
    
    public void reset(int playerId) {
        Integer score = playerScoreMap.get(playerId);
        if (null != score) clearPlayer(playerId, board[score]);
    }
}

/**
 * Your Leaderboard object will be instantiated and called as such:
 * Leaderboard obj = new Leaderboard();
 * obj.addScore(playerId,score);
 * int param_2 = obj.top(K);
 * obj.reset(playerId);
 */
//leetcode submit region end(Prohibit modification and deletion)
