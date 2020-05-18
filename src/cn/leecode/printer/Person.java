package cn.leecode.printer;

/**
 * 描述:
 * 测试类
 *
 * @author HeGaoJian
 * @version 1.0
 * @create 2020-05-18 19:30
 */
public class Person implements Comparable<Person> {
    private Integer age;

    public Person(Integer age) {
        this.age = age;
    }

    @Override
    public int compareTo(Person person) {
        //假定年龄比较大的就返回
//        if (this.age > person.age) {
//            return 1;
//        }
//        if (age < person.age) {
//            return -1;
//        }
//        return 0;
        return age - person.age;
    }
}
