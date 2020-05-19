package cn.leecode.binarytree;

import cn.leecode.binarytree.printer.BinaryTrees;

/**
 * 描述:
 * // 两个比较器的关系的理解
 * // 比较器写法的理解
 *
 * @author HeGaoJian
 * @version 1.0
 * @create 2020-05-18 19:25
 */
public class Main {
    /**
     * 创建搜索器的比较器 创建第一个比较器
     */

    private static class PersonComparator implements Comparator<Person> {
        @Override
        public int compare(Person e1, Person e2) {
            return e1.getAge() - e2.getAge();
        }
    }

    /**
     * 两个比较的逻辑是不一样的
     */
    private static class PersonComparator2 implements Comparator<Person> {
        @Override
        public int compare(Person e1, Person e2) {
            return e2.getAge() - e1.getAge();
        }
    }

    /**
     * 测试 二叉树 并且打印相关数据
     */
    static void test1() {
        Integer data[] = new Integer[]{
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

        BinaryTrees.println(bst);
    }

    static void test5() {
        BinarySearchTree<Person> bst = new BinarySearchTree<>();
        bst.add(new Person(10, "jack"));
        bst.add(new Person(12, "rose"));
        bst.add(new Person(6, "jim"));

        bst.add(new Person(10, "michael"));

        BinaryTrees.println(bst);
    }

    static void test6() {
        Integer data[] = new Integer[]{
                7, 4, 9, 2, 1, 3, 5, 9, 8, 11, 10, 12
        };

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }
        BinaryTrees.println(bst);
        bst.levelOrderTraversal();

//		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
//		for (int i = 0; i < 10; i++) {
//			bst.add((int)(Math.random() * 100));
//		}
//        BinaryTrees.println(bst);
//        System.out.println(bst.isComplete());

        // bst.levelOrderTraversal();

		/*
		 *       7
		 *    4    9
		    2   5
		 */

//		bst.levelOrder(new Visitor<Integer>() {
//			public void visit(Integer element) {
//				System.out.print("_" + element + "_ ");
//			}
//		});

//		bst.inorder(new Visitor<Integer>() {
//			public void visit(Integer element) {
//				System.out.print("_" + (element + 3) + "_ ");
//			}
//		});

        // System.out.println(bst.height());
    }


    public static void main(String[] args) {
        /**
         * 问题可能存在针对每一个的逻辑
         *
         */
//        BinarySearchTree<Person> tree = new BinarySearchTree<>(new PersonComparator());

        /**
         * 官方的匿名类
         *  内置的类型就会实现默认的比较器 String Integer 等之类的类型
         */
////       new BinarySearchTree<>(new Comparator<>())
//        BinarySearchTree<Person> tree = new BinarySearchTree<>(new java.util.Comparator<Person>() {
//            //匿名的内部类
//            //相当于传一个比较器的方法 告诉你应该怎么比
//            @Override
//            public int compare(Person o1, Person o2) {
//                return 0;
//            }
//        });
//        /**
//         * java 8 lambda 表达式
//         */
//        BinarySearchTree<Person> lamda = new BinarySearchTree<Person>(
//                (p1, p2) -> p1.getAge().compareTo(p2.getAge())
//        );
//
//        test1();

        test5();

    }


}
