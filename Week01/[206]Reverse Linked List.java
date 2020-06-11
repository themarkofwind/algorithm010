//Reverse a singly linked list. 
//
// Example: 
//
// 
//Input: 1->2->3->4->5->NULL
//Output: 5->4->3->2->1->NULL
// 
//
// Follow up: 
//
// A linked list can be reversed either iteratively or recursively. Could you im
//plement both? 
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


    public ListNode reverseList(ListNode head) {

        if (null == head) {
            return null;
        }

        ListNode pre = null;
        ListNode cur = head;
        while (null != cur) {
            ListNode nextNode = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nextNode;
        }
        // 最终状态cur为null，头结点为pre
        return pre;
    }

    public ListNode reverseList1(ListNode head) {

        if (null == head) {
            return null;
        }

        ListNode pre = null;
        ListNode cur = head;
        while (null != cur.next) {
            ListNode p = cur;
            cur = cur.next;
            p.next = pre;
            pre = p;
        }

        cur.next = pre;
        return cur;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
