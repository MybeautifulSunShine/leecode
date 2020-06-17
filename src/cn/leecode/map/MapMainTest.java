package cn.leecode.map;

import cn.leecode.map.model.Key;

public class MapMainTest {

    public static void main(String[] args) {
//        Person person = new Person(10, 1.67f, "jack");
//        Person person2 = new Person(10, 1.67f, "jack");
        HashMap<Object, Integer> map = new HashMap<>();
//        map.put(person, 1);
//        map.put(person2, 2);
//        map.put("jack", 3);
//        map.put("rose", 4);
//        map.put("jack", 5);
//        map.put(null, 6);
        for (int i = 1; i <= 19; i++) {
//            map.put("test" + i, i);
            map.put(new Key(i), i);
        }
        map.put(new Key(4), 100);
        map.print();
        System.out.println(map.get(new Key(4)));
//        map.print();
//        System.out.println(map.get("jack"));
//        System.out.println(map.get("rose"));
//        System.out.println(map.get(null));
//        System.out.println(map.get(person));
//        System.out.println(map.size());
//        System.out.println(map.remove("jack"));
//        System.out.println(map.get("jack"));
//        System.out.println(map.size());
//        map.traversal(new Map.Visitor<Object, Integer>() {
//            @Override
//            public boolean visit(Object key, Integer value) {
//                System.out.println("key" + key + ": " + value);
//                return false;
//            }
//        });
//        System.out.println(map.containsKey(person));
//        System.out.println(map.containsKey("jack"));
//        System.out.println(map.containsKey(null));
//        System.out.println(map.containsValue(1));
//        System.out.println(map.containsValue(4));

    }
}
