package Link;

/**
 * 描述:
 * 环形链表
 * https://leetcode-cn.com/problems/linked-list-cycle/
 *
 * @author HeGaoJian
 * @version 1.0
 * @create 2019-07-19 14:16
 */
public class LeeCode141 {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
//        [1,2,6,3,4,5,6]
//        6
    }
}
