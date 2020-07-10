package src.tree;

/**
 * Created by Administrator on 2020/5/20.
 * <p>
 * https://leetcode-cn.com/problems/invert-binary-tree/
 */
public class LeeCode_226 {
    public src.tree.TreeNode invertTree(src.tree.TreeNode root) {
        if (root == null) {
            return root;
        }

        /**
         * 前序遍历
         */
        invertTree(root.left);
        invertTree(root.right);

        return null;
    }
}
