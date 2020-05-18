package cn.leecode.printer;

/**
 * 描述:
 * 测试
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
//       new BinarySearchTree<>(new Comparator<>())
        BinarySearchTree<Person> tree = new BinarySearchTree<>(new java.util.Comparator<Person>() {
            //匿名的内部类
            //相当于传一个比较器的方法 告诉你应该怎么比
            @Override
            public int compare(Person o1, Person o2) {
                return 0;
            }
        });


    }


}
