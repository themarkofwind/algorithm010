//A robot on an infinite grid starts at point (0, 0) and faces north. The robot 
//can receive one of three possible types of commands: 
//
// 
// -2: turn left 90 degrees 
// -1: turn right 90 degrees 
// 1 <= x <= 9: move forward x units 
// 
//
// Some of the grid squares are obstacles. 
//
// The i-th obstacle is at grid point (obstacles[i][0], obstacles[i][1]) 
//
// If the robot would try to move onto them, the robot stays on the previous gri
//d square instead (but still continues following the rest of the route.) 
//
// Return the square of the maximum Euclidean distance that the robot will be fr
//om the origin. 
//
// 
//
// Example 1: 
//
// 
//Input: commands = [4,-1,3], obstacles = []
//Output: 25
//Explanation: robot will go to (3, 4)
// 
//
// 
// Example 2: 
//
// 
//Input: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
//Output: 65
//Explanation: robot will be stuck at (1, 4) before turning left and going to (1
//, 8)
// 
// 
//
// 
//
// Note: 
//
// 
// 0 <= commands.length <= 10000 
// 0 <= obstacles.length <= 10000 
// -30000 <= obstacle[i][0] <= 30000 
// -30000 <= obstacle[i][1] <= 30000 
// The answer is guaranteed to be less than 2 ^ 31. 
// 
// Related Topics Greedy


import java.util.HashSet;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        if (null == commands || commands.length <= 0) return 0;
        Set<String> obstacleSet = new HashSet<>();
        if (null != obstacles) {
            for (int[] obs : obstacles) {
                obstacleSet.add(generateKey(obs[0], obs[1]));
            }
        }
        String direction = "N";
        int[] dot = new int[]{0, 0};
        int max = 0;
        for (int c : commands) {
            if (c == -2) direction = nextDirection(direction, true);
            if (c == -1) direction = nextDirection(direction, false);
            // forwards
            if (c >= 1) {
                for (int m = 0; m < c; m++) {
                    dot = move(direction, dot[0], dot[1], obstacleSet);
                }
            }
            max = Math.max(max, dot[0] * dot[0] + dot[1] * dot[1]);
        }
        return max;
    }

    // turn left or right to make a new direction
    private String nextDirection(String direction, boolean turnLeft) {
        switch (direction) {
            case "N": return turnLeft ? "W" : "E";
            case "E": return turnLeft ? "N" : "S";
            case "S": return turnLeft ? "E" : "W";
            case "W": return turnLeft ? "S" : "N";
        }
        return direction;
    }

    // move one step forward in direction
    // if the next is obstacle then stop
    private int[] move(String direction, int i, int j, Set<String> obstacleSet) {
        switch (direction) {
            case "N": if (!obstacleSet.contains(generateKey(i, j + 1))) {j++;}break;
            case "E": if (!obstacleSet.contains(generateKey(i + 1, j))) {i++;}break;
            case "S": if (!obstacleSet.contains(generateKey(i, j - 1))) {j--;}break;
            case "W": if (!obstacleSet.contains(generateKey(i - 1, j))) {i--;}break;
        }
        return new int[]{i, j};
    }

    private String generateKey(int i, int j) {
        return i + "_" + j;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
