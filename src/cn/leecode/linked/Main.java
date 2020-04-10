package cn.leecode.linked;

import cn.leecode.circle.SingleCircleLinkedList;

/**
 * @author SJ217110601
 */
public class Main {
    static void testList(MyList<Integer> list) {

        list.add(11);
        list.add(22);
        list.add(33);
        list.add(44);

        list.add(0, 55);
        // [55, 11, 22, 33, 44]

        list.add(2, 66);
        // [55, 11, 66, 22, 33, 44]
        list.add(list.size(), 77);
        //         [55, 11, 66, 22, 33, 44, 77]
        list.remove(0);
        //         [11, 66, 22, 33, 44, 77]
        System.out.println(list);
        list.remove(2);
        //         [11, 66, 33, 44, 77]
        System.out.println(list);
        list.remove(list.size() - 1);
        System.out.println(list);
        //         [11, 22, 33, 44]
        assert list.indexOf(44) == 3;
        assert list.indexOf(22) == -1;
        assert list.contains(33);
        assert list.get(0) == 11;
        assert list.get(1) == 66;
        assert list.get(list.size() - 1) == 44;

        System.out.println(list);
    }

    public static void main(String[] args) {
        testList(new SingleCircleLinkedList<>());
    }
}
