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

    public ListNode removeElements(ListNode head, int val) {
        ListNode seeNode = new ListNode(0);
        seeNode.next = head;
        ListNode prev = seeNode, curr = head;
        while (curr != null) {
            if (curr.val == val) {
                prev.next = curr.next;
            } else {
                prev = curr;
            }
            curr = curr.next;
        }
        return seeNode.next;
    }

    public ListNode removeElements2(ListNode head, int val) {
        ListNode newListNode = new ListNode(0);
        newListNode.next = head;
        ListNode p = newListNode;
        while (newListNode.next != null) {
            if (newListNode.next.val == val) {
                newListNode.next = newListNode.next.next;
            } else {
                newListNode = newListNode.next;
            }
        }
        return p.next;
    }

    static ListNode deleteDuplicates(ListNode head) {
        // 1->1->2
        ListNode curr = head;
        while (curr != null && curr.next != null) {
            if (curr.next.val == curr.val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return head;
    }

    static ListNode middleNode(ListNode head) {
        ListNode newNode = head;
        int size = 0;
        while (newNode != null) {
            ++size;
            newNode = newNode.next;
        }
        int t = 0;
        newNode = head;
        while (t < size / 2) {
            ++t;
            newNode = newNode.next;
        }
        return newNode;
    }


    public static void main(String[] args) {
//        ListNode head = new ListNode(1);
//        ListNode node1 = new ListNode(1);
//        ListNode node2 = new ListNode(2);
////        ListNode node3 = new ListNode(1);
//        head.next = (node1);
//        node1.next = (node2);
////        node2.next = (node3);
//        System.out.println(deleteDuplicates(head).toString());
        System.out.println(7 % 2);
    }
}
