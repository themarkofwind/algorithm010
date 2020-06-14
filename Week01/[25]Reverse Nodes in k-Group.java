//Given a linked list, reverse the nodes of a linked list k at a time and return
// its modified list. 
//
// k is a positive integer and is less than or equal to the length of the linked
// list. If the number of nodes is not a multiple of k then left-out nodes in the 
//end should remain as it is. 
//
// 
// 
//
// Example: 
//
// Given this linked list: 1->2->3->4->5 
//
// For k = 2, you should return: 2->1->4->3->5 
//
// For k = 3, you should return: 3->2->1->4->5 
//
// Note: 
//
// 
// Only constant extra memory is allowed. 
// You may not alter the values in the list's nodes, only nodes itself may be ch
//anged. 
// 
// Related Topics Linked List


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    // hair ->  1  ->  2  ->  3  ->  4  ->  5
    //  pre ->  s             e
    // hair ->               pre     s      e
    public ListNode reverseKGroup(ListNode head, int k) {
        if (null == head || k <= 1) {
            return head;
        }
        ListNode hair = new ListNode(-1);
        hair.next = head;
        ListNode pre = hair;
        while (null != pre) {
            // 反转子链表头节点
            ListNode start = pre.next;
            // 找反转子链表尾节点
            ListNode end = pre;
            int m = 0;
            // 反转子链表的尾节点必须不为空
            while (m < k && null != end.next) {
                end = end.next;
                m++;
            }
            if (m == k) {
                ListNode[] nodes = reverseK(start, end, k);
                pre.next = nodes[0];
                pre = nodes[1];
            } else {
                break;
            }
        }
        return hair.next;
    }

    // reverse k nodes
    // 返回反转后的链表头、尾节点
    private ListNode[] reverseK(ListNode start, ListNode end, int k) {
        // 反转链表的尾节点，自动指向下一段的头节点
        ListNode pre = end.next;
        ListNode cur = start;
        int i = 0;
        while (i < k) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
            i++;
        }
        return new ListNode[]{pre, start};
    }
}
//leetcode submit region end(Prohibit modification and deletion)
