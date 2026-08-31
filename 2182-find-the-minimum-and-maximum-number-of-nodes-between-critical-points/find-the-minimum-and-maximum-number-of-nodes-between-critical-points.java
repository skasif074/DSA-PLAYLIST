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
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int firstIdx = -1, prevIdx = -1, idx = 1;
        int minDist = Integer.MAX_VALUE;
        ListNode prev = head, curr = head.next;
        
        while (curr.next != null) {
            if ((curr.val > prev.val && curr.val > curr.next.val) ||
                (curr.val < prev.val && curr.val < curr.next.val)) {
                if (firstIdx == -1) {
                    firstIdx = idx;
                } else {
                    minDist = Math.min(minDist, idx - prevIdx);
                }
                prevIdx = idx;
            }
            prev = curr;
            curr = curr.next;
            idx++;
        }
        
        if (prevIdx == -1 || prevIdx == firstIdx) {
            return new int[]{-1, -1};
        }
        
        return new int[]{minDist, prevIdx - firstIdx};
    }
}