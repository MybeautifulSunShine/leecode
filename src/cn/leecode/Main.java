package cn.leecode;

import cn.leecode.linked.MyLinkedList;

public class Main {

    public static void main(String[] args) {
        MyLinkedList<Object> linkedList = new MyLinkedList<>();
        linkedList.add("aaaa");

//        new HashMap<>();
//        String[] order = {"语文", "数学", "英语", "物理", "化学", "生物", "政治", "历史", "地理", "总分"};
//        final List<String> definedOrder = Arrays.asList(order);
//        List<String> list = new ArrayList<String>() {
//            {
//                add("总分");
//                add("英语");
//                add("政治");
//                add("总分");
//                add("数学");
//            }
//        };
//
//        Collections.sort(list, new Comparator<String>() {
//
//            @Override
//            public int compare(String o1, String o2) {
//                int io1 = definedOrder.indexOf(o1);
//                int io2 = definedOrder.indexOf(o2);
//                int aa = io1 - io2;
//                System.out.println(aa);
//                return aa;
//            }
//        });
//
//        for (String s : list) {
//            System.out.print(s + "   ");
//        }
//    }
//        int intBits = Float.floatToIntBits(6.21f);
//        System.out.println(intBits);
//        Integer aa =  5489;
//        System.out.println(Integer.toBinaryString(intBits));
//        System.out.println(aa.hashCode());
        //double  和 float
        System.out.println(5>>1);
    }
}
