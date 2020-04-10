package Link;

/**
 * 描述: 反转一个链表
 * https://leetcode-cn.com/problems/reverse-linked-list/
 *
 * @author HeGaoJian
 * @version 1.0
 * @create 2019-07-18 10:04
 */
public class LeeCode206 {
    /**
     * 递归的方式
     *
     * @param head
     * @return
     */
    static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newReverList = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newReverList;
    }

    /**
     * 链表第二方式
     */
    static ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = null;
        while (head != null) {
            ListNode tmp = head.next;
            head.next = newHead;
            newHead = head;
            head = tmp;
        }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(1);
        head.next = (node1);
        node1.next = (node2);
        node2.next = (node3);
        System.out.println(LeeCode206.reverseList(head).toString());
    }
}
