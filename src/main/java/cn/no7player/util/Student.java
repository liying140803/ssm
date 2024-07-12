package cn.no7player.util;

import java.util.Objects;

/**
 * Created by liying
 * Date 2020-01-15
 */


public class Student {
    //学生类
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }
}
