package cn.leecode.printer;

import java.lang.*;

/**
 * 描述:
 * 测试类
 *
 * @author HeGaoJian
 * @version 1.0
 * @create 2020-05-18 19:30
 */
public class Person implements java.lang.Comparable<Person>{
    private Integer age;

    public Person(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    @Override
    public int compareTo(Person o) {
        return 0;
    }
}
