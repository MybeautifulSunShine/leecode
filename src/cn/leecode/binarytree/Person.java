package cn.leecode.binarytree;

/**
 * 描述:
 * 测试类
 *
 * @author HeGaoJian
 * @version 1.0
 * @create 2020-05-18 19:30
 */

public class Person implements java.lang.Comparable<Person> {
    private Integer age;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person(Integer age, String name) {
        this.age = age;
        this.name = name;

    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    @Override
    public int compareTo(Person o) {
        return age - o.age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
