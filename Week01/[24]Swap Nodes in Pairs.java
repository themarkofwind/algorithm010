//Given a linked list, swap every two adjacent nodes and return its head. 
//
// You may not modify the values in the list's nodes, only nodes itself may be c
//hanged. 
//
// 
//
// Example: 
//
// 
//Given 1->2->3->4, you should return the list as 2->1->4->3.
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
    // dummy(t) -> 1(start/t.next) -> 2(end/t.next.next) -> 3
    // t.next = end
    // start.next = end.next
    // end.next = start
    // t = start
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode tmp = dummy;
        while (null != tmp.next && null != tmp.next.next) {
            ListNode start = tmp.next;
            ListNode end = tmp.next.next;
            tmp.next = end;
            start.next = end.next;
            end.next = start;
            tmp = start;
        }
        return dummy.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
