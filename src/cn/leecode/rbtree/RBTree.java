package cn.leecode.rbtree;

import java.util.Comparator;

/**
 * 描述:
 * 红黑树
 *
 * @author HeGaoJian
 * @version 1.0
 * @create 2020-06-02 17:33
 */
public class RBTree<E> extends BBST<E> {
    /**
     * 定义常量  红色为false
     * 黑色为true
     */
    private static final boolean RED = false;
    private static final boolean BLACK = true;


    public RBTree() {
    }

    public RBTree(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * 添加跟删除之后 也要看一下整个树是不是平衡
     *
     * @param node 新添加的节点
     */
    @Override
    protected void afterAdd(Node<E> node) {
        //拿到相对应的节点进行判断
        Node<E> parent = node.parent;
        /**
         * 添加的是跟节点 ,或者上溢到达了跟节点
         */
        if (parent == null) {
            black(node);
            return;
        }
        /**
         * 判断如果是黑色节点方法直接添加
         */
        if (isBlack(parent)) {
            return;
        }
        /**
         * uncle
         */
        Node<E> uncle = parent.sibling();
        /**
         * grand 只要拿到grand 就是要把节点染成红色
         */
        Node<E> grand = red(parent.parent);
        /**
         * uncle is red 节点要上溢
         */
        if (isRed(uncle)) {
            black(parent);
            black(uncle);
            //grand 向上合并
            //把祖父结单当做是新添加的节点
            afterAdd(grand);
            return;
        }
        /**
         *  uncle is not red
         */
        /**
         * is  or ll rr
         */

        if (parent.isLeftChild()) {
            //l
            if (node.isLeftChild()) {
                //ll
                black(parent);
            } else {
                //lr
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else { //R
            if (node.isLeftChild()) {
                //rl
                black(node);
                rotateRight(parent);
            } else {
                //rr
                black(parent);
            }
            rotateLeft(grand);
        }

    }

    /**
     * @param node 被删除的节点
     */
    @Override
    protected void afterRemove(Node<E> node) {
        //判断取代它的子节点是不是红色 也就是46,76  -->所以要对方法进行对应的调整
        //但是要拿到 left
        //否则删除
        /**
         * delete node is red
         * 用以取代node的子节点是红色 black to replacement
         *
         */
        if (isRed(node)) {
            black(node);
            return;
        }
        Node<E> parent = node.parent;
        if (parent == null) {
            return;
        }
        /**
         * 1 delete node is black childNode
         * 2 take if brother color
         * 3 Come here must is childNode
         * 4 node is childNode
         *  node is be deleted
         *  node is black
         */
        /**
         * if delete node is left or right
         * @see  BST#remove(cn.leecode.rbtree.BinaryTree.Node<E>) 134 line
         */
        boolean left = parent.left == null || node.isLeftChild();
        Node<E> sibling = left ? parent.right : parent.left;
        //be delete node in parent.left , brother node is parent.right
        if (left) {
            //be delete node in parent.right ,brother node is parent.left
            //brother node is  red
            if (isRed(sibling)) {
                black(sibling);
                red(parent);
                rotateLeft(parent);
                //replace brother
                sibling = parent.right;
            }
            //brother node must be is black node
            //look brother has node or no  borrow this node
            //brother node not hat one red node , parent node merge down to sibling
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // Judge parent node color
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent);
                }
                /**
                 * brother node at least has one red node
                 * in BTree see borrow  node
                 * @Link {https://cdn.jsdelivr.net/gh/MybeautifulSunShine/images/image-20200604161549325.png}
                 */
            } else {
                /**
                 * brother left is black,兄弟节点要进行旋转
                 */
                if (isBlack(sibling.right)) {
                    rotateRight(sibling);
                    sibling = parent.right;
                }
                //brother not extend parent color
                color(sibling, colorOf(parent));
                black(sibling.right);
                black(parent);
                rotateLeft(parent);

            }
        } else {
            //brother node is  red
            if (isRed(sibling)) {
                black(sibling);
                red(parent);
                rotateRight(parent);
                //replace brother
                sibling = parent.left;
            }
            //brother node must be is black node
            //look brother has node or no  borrow this node
            //brother node not hat one red node , parent node merge down to sibling
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // Judge parent node color
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent);
                }
                /**
                 * brother node at least has one red node
                 * in BTree see borrow  node
                 * @Link {https://cdn.jsdelivr.net/gh/MybeautifulSunShine/images/image-20200604161549325.png}
                 */
            } else {
                /**
                 * brother left is black,兄弟节点要进行旋转
                 */
                if (isBlack(sibling.left)) {
                    rotateLeft(sibling);
                    sibling = parent.left;
                }
                //brother not extend parent color
                color(sibling, colorOf(parent));
                black(sibling.left);
                black(parent);
                rotateRight(parent);

            }
        }
    }

    // 如果删除的节点是红色
    //v 1
//    @Override
//    protected void afterRemove(Node<E> node) {
//		if (isRed(node)) return;
//
//		// 用以取代node的子节点是红色
//		if (isRed(replacement)) {
//			black(replacement);
//			return;
//		}
//
//		Node<E> parent = node.parent;
//		// 删除的是根节点
//		if (parent == null) return;
//
//		// 删除的是黑色叶子节点【下溢】
//		// 判断被删除的node是左还是右
//		boolean left = parent.left == null || node.isLeftChild();
//		Node<E> sibling = left ? parent.right : parent.left;
//		if (left) { // 被删除的节点在左边，兄弟节点在右边
//			if (isRed(sibling)) { // 兄弟节点是红色
//				black(sibling);
//				red(parent);
//				rotateLeft(parent);
//				// 更换兄弟
//				sibling = parent.right;
//			}
//
//			// 兄弟节点必然是黑色
//			if (isBlack(sibling.left) && isBlack(sibling.right)) {
//				// 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
//				boolean parentBlack = isBlack(parent);
//				black(parent);
//				red(sibling);
//				if (parentBlack) {
//					afterRemove(parent, null);
//				}
//			} else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
//				// 兄弟节点的左边是黑色，兄弟要先旋转
//				if (isBlack(sibling.right)) {
//					rotateRight(sibling);
//					sibling = parent.right;
//				}
//
//				color(sibling, colorOf(parent));
//				black(sibling.right);
//				black(parent);
//				rotateLeft(parent);
//			}
//		} else { // 被删除的节点在右边，兄弟节点在左边
//			if (isRed(sibling)) { // 兄弟节点是红色
//				black(sibling);
//				red(parent);
//				rotateRight(parent);
//				// 更换兄弟
//				sibling = parent.left;
//			}
//
//			// 兄弟节点必然是黑色
//			if (isBlack(sibling.left) && isBlack(sibling.right)) {
//				// 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
//				boolean parentBlack = isBlack(parent);
//				black(parent);
//				red(sibling);
//				if (parentBlack) {
//					afterRemove(parent, null);
//				}
//			} else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
//				// 兄弟节点的左边是黑色，兄弟要先旋转
//				if (isBlack(sibling.left)) {
//					rotateLeft(sibling);
//					sibling = parent.left;
//				}
//
//				color(sibling, colorOf(parent));
//				black(sibling.left);
//				black(parent);
//				rotateRight(parent);
//			}
//		}
//	}
//}


    /**
     * 判断是否为黑色
     */
    private boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    /**
     * 判断是否为为红色
     */
    private boolean isRed(Node<E> node) {
        return colorOf(node) == RED;

    }

    /**
     * 染色的方法 传一个颜色
     */
    private RBNode<E> color(Node<E> node, boolean color) {
        if (node != null) {
            ((RBNode<E>) node).color = color;
        }
        return (RBNode<E>) node;
    }

    /**
     * 染为黑色
     */
    private RBNode<E> black(Node<E> node) {
        return color(node, BLACK);
    }

    /**
     * 染为红色
     */
    private RBNode<E> red(Node<E> node) {
        return color(node, RED);
    }

    /**
     * @Description 判断节点的颜色
     **/
    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RBNode<E>) node).color;
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<>(element, parent);
    }

    private static class RBNode<E> extends Node<E> {
        //定义常量颜色
        boolean color = RED;

        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }

        @Override
        public String toString() {
            String str = "";
            if (color == RED) {
                str = "R_";
            }
            return str + element.toString();
        }
    }

}
