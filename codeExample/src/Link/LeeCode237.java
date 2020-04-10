package Link;

/**
 * 描述: 删除链表的一个节点
 * <a >https://leetcode-cn.com/problems/delete-node-in-a-linked-list/</a>
 *
 * @author HeGaoJian
 * @version 1.0
 * @create 2019-07-17 18:11
 */
public class LeeCode237 {

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
