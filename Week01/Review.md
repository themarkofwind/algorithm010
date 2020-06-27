#  算法训练营第一周Review

## Leetcode 283: Move Zeros

双指针解法：

- i -> 0，nums[i] == 0 保持不动，nums[i] == 1 i后移，找到第一个要后移的 `0`
- j -> [0, length)，找到第一个不为`0`的元素
- swap(i, j)，交换i和j位置元素，实现在前面的`0`后移
- **注意：** 交换后，i位置不为0，j位置为0，此时i和j都需要后移

**时间**复杂度：`O(n)`

**空间**复杂度：`O(N)`

**额外空间**复杂度：`O(1)`

| i    |      |      |      |      |
| ---- | ---- | ---- | ---- | ---- |
| 0    | 1    | 0    | 3    | 12   |
| j    |      |      |      |      |

```java
class Solution {
    // 双指针 review in 2020.06.27
    public void moveZeroes(int[] nums) {
        if (null == nums || nums.length <=0 ) return;
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[i] == 0 && nums[j] != 0) {
                swap(nums, i, j);
                // i和j交换后，i需要后移!
                i++;
            } else if (nums[i] != 0) {
                i++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```

## Leetcode 15: Three Sum

排序 + 双指针解法（注意结果需要判重）：

- nums[] 求解 a + b + c = 0，可转化为：a + b = -c
- nums进行排序，c -> [0, length - 2)
- a -> c + 1, b -> length - 1
- `a + b + c < 0` 说明a不够大，需要后移
- `a + b + c > 0` 说明b不够小，需要前移
- `a + b + c == 0` 可行解
- **结果去重：** a, b, c都需要去重，比较当前值与上一个值是否相同，相同则跳过

**时间**复杂度：`O(N^2)` 忽略排序的时间复杂度`O(NlogN)`

**空间**复杂度：`O(N)`

**额外空间**复杂度：`O(logN)` 排序带来的额外空间复杂度

| -4   | -1   | -1   | 0    | 1    | 2    |
| ---- | ---- | ---- | ---- | ---- | ---- |
| c    | a    |      |      |      | b    |

```java
class Solution {
    // sort + 双指针
    // a + b = -c
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == nums || nums.length < 3) return result;
        Arrays.sort(nums);
        for (int c = 0; c < nums.length - 2; c++) {
            if (nums[c] > 0) break;
            if (c > 0 && nums[c] == nums[c - 1]) continue;
            int a = c + 1;
            int b = nums.length - 1;
            while (a < b) {
                if (nums[a] + nums[b] + nums[c] < 0) {
                    while (a < b && nums[a] == nums[++a]);
                } else if (nums[a] + nums[b] + nums[c] > 0) {
                    while (a < b && nums[b] == nums[--b]);
                } else {
                    result.add(new ArrayList<>(Arrays.asList(nums[c], nums[a], nums[b])));
                    while (a < b && nums[a] == nums[++a]);
                    while (a < b && nums[b] == nums[--b]);
                }
            }
        } 
        return result;
    }
}
```

## Leetcode 239: Sliding window maximum

```tex
输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
输出: [3,3,5,5,6,7] 
解释: 

  滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
```

双端队列 + 保持队列中元素从大到小排列：

- deque头结点永远为最大的元素（下标），若此下标已不在当前滑动窗口内，需要剔除
- 新入队列的元素，需要跟deque的尾结点对比，大于则尾结点出队，保证队列从大到小顺序
- `i` 从`k-1`开始为滑动窗口的起始，向结果输出最大值

**时间**复杂度：`O(N)` 

**空间**复杂度：`O(N)`

**额外空间**复杂度：`O(k)`

```java
class Solution {
    // review in 2020.06.27
    // deque + 保证队列中元素由大到小排列
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (null == nums || nums.length < k || k <= 0) return new int[0];
        // 双端队列
        LinkedList<Integer> deque = new LinkedList<>();
        int[] result = new int[nums.length - k + 1];
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            // 队列头结点已不在滑动窗口内
            while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            // 队列尾结点小于将要入队的结点值
            // 弹出队列，保证队列中按从大到小排列
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            if (i >= k - 1) result[j++] = nums[deque.peekFirst()];
        }
        return result;
    }
}
```

