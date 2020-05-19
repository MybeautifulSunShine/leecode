package cn.leecode.binarytree.printer;

/**
 * @author HGJ 打印的工具与方法
 */
public abstract class BasePrinter {
    /**
     * 二叉树的基本信息
     */
    protected BinaryTreeInfo tree;

    public BasePrinter(BinaryTreeInfo tree) {
        this.tree = tree;
    }

    /**
     * 生成打印的字符串
     */
    public abstract String printString();

    /**
     * 打印后换行
     */
    public void println() {
        print();
        System.out.println();
    }

    /**
     * 打印
     */
    public void print() {
        System.out.print(printString());
    }
}
