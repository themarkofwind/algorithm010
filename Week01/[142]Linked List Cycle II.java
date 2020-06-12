//Given a linked list, return the node where the cycle begins. If there is no cy
//cle, return null. 
//
// To represent a cycle in the given linked list, we use an integer pos which re
//presents the position (0-indexed) in the linked list where tail connects to. If 
//pos is -1, then there is no cycle in the linked list. 
//
// Note: Do not modify the linked list. 
//
// 
//
// Example 1: 
//
// 
//Input: head = [3,2,0,-4], pos = 1
//Output: tail connects to node index 1
//Explanation: There is a cycle in the linked list, where tail connects to the s
//econd node.
// 
//
// 
//
// Example 2: 
//
// 
//Input: head = [1,2], pos = 0
//Output: tail connects to node index 0
//Explanation: There is a cycle in the linked list, where tail connects to the f
//irst node.
// 
//
// 
//
// Example 3: 
//
// 
//Input: head = [1], pos = -1
//Output: no cycle
//Explanation: There is no cycle in the linked list.
// 
//
// 
//
// 
//
// Follow-up: 
//Can you solve it without using extra space? 
// Related Topics Linked List Two Pointers


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {

    // 环外节点个数：a，环内节点个数：b
    // 快指针f，每次走两步
    // 慢指针s，每次走一步
    // f = 2s
    // 快慢指针环内相遇，快指针比慢指针多走nb步
    // f = s + nb
    // => s = nb 慢指针走了nb
    // => f = 2nb 快指针走了2nb
    // 环的入口：从head走a步，或者(a + mb)步
    // 快慢指针相遇时，慢指针走了nb，再走a步就能回到环的入口
    // 此时再设置一个指针，从head开始走，每次一步，走a步后与慢指针相遇（环的入口）
    public ListNode detectCycle(ListNode head) {
        if (null == head) {
            return null;
        }
        ListNode f = head;
        ListNode s = head;
        while (null != f && null != f.next) {
            f = f.next.next;
            s = s.next;
            // 判断是否有环
            if (f == s) {
                // k从head开始，与s相遇即为环入口
                ListNode k = head;
                while (k != s) {
                    k = k.next;
                    s = s.next;
                }
                return k;
            }
        }

        return null;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
