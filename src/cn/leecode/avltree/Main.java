package cn.leecode.avltree;

import cn.leecode.binarytree.printer.BinaryTrees;
import cn.leecode.refactorbinaty.BST;

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("unused")
public class Main {

    static void test1() {
        Integer data[] = new Integer[]{
                35, 11, 49, 52, 3, 54, 2, 15, 64
        };

        AvlTree<Integer> avl = new AvlTree<>();
        for (int i = 0; i < data.length; i++) {
            avl.add(data[i]);
//			System.out.println("【" + data[i] + "】");
//			BinaryTrees.println(avl);
//			System.out.println("---------------------------------------");
        }

//		for (int i = 0; i < data.length; i++) {
//			avl.remove(data[i]);
//			System.out.println("【" + data[i] + "】");
//			BinaryTrees.println(avl);
//			System.out.println("---------------------------------------");
//		}


        BinaryTrees.println(avl);
    }

    static void test2() {
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < 100_0000; i++) {
            data.add((int) (Math.random() * 100_0000));
        }

        BST<Integer> bst = new BST<>();
        for (int i = 0; i < data.size(); i++) {
            bst.add(data.get(i));
        }
        for (int i = 0; i < data.size(); i++) {
            bst.contains(data.get(i));
        }
        for (int i = 0; i < data.size(); i++) {
            bst.remove(data.get(i));
        }

        AvlTree<Integer> avl = new AvlTree<>();
        for (int i = 0; i < data.size(); i++) {
            avl.add(data.get(i));
        }
        for (int i = 0; i < data.size(); i++) {
            avl.contains(data.get(i));
        }
        for (int i = 0; i < data.size(); i++) {
            avl.remove(data.get(i));
        }
    }

    public static void main(String[] args) {
        test1();


    }
}
