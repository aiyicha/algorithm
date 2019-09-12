package com.aiyicha.algorithm.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    Object oo;

    @RequestMapping("hello")
    public Person hello() {

        return new Person("Jack", 12);
    }

    @RequestMapping("save")
    public String save(@RequestBody Object object) {

        oo = object;
        return "success";
    }

    @RequestMapping("info")
    public Object info() {

        return oo;
    }

    public class Person {
        String name;

        Integer age;

        Person (String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
