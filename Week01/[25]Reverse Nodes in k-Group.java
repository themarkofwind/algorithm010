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

    // optimize
    // k = 4
    // hair -> 1 -> 2 -> 3 -> 4 -> 5 -> 6
    // prev    s              e
    //
    // hair -> 4 -> 3 -> 2 -> 1 -> 5 -> 6
    //                       prev
    //                             s(e == null循环结束) 
    public ListNode reverseKGroup(ListNode head, int k) {
        if (null == head || k <= 0) return head;
        ListNode hair = new ListNode(-1);
        hair.next = head;
        ListNode prev = hair;
        while (null != prev) {
            // 批次找出要反转的k个结点
            // k个结点链表起始位置
            ListNode start = prev.next;
            // k个结点链表结束位置
            ListNode end = prev;
            int i = 0;
            while (i++ < k && null != end) {
                end = end.next;
            }
            // end不为null说明可以反转
            if (null != end) {
                // 反转start -> end
                reverseK(start, end, k);
                // 反转后原来的end变成k链表的头结点
                prev.next = end;
                // prev指向处理完的k链表尾结点，继续下一个迭代
                prev = start;
            } else {
                // 最后的结点个数不够k个，不处理
                prev = null;
            }
        }
        
        return hair.next;
    }
    
    // reverse k nodes list
    private void reverseK(ListNode start, ListNode end, int k) {
        // prev设为下一段链表的头结点
        // 这样反转链表后，k链表的尾结点将自动指向下一段链表的头结点
        ListNode prev = end.next;
        ListNode cur = start;
        int i = 0;
        while (i++ < k) {
            ListNode tmp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmp;
        }
    }

    // hair ->  1  ->  2  ->  3  ->  4  ->  5
    //  pre ->  s             e
    // hair ->               pre     s      e
    public ListNode reverseKGroup1(ListNode head, int k) {
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
                ListNode[] nodes = reverseK1(start, end, k);
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
    private ListNode[] reverseK1(ListNode start, ListNode end, int k) {
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
