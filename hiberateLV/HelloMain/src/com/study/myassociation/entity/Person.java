package com.study.myassociation.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by chenlian on 16/6/16.
 */
@Entity(name = "Person")
@Table(name = "T_Person")
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    //mappedBy作用是不产生第三个表
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persons")
    private List<Address> address = new ArrayList<>();

//    @OneToOne(cascade = CascadeType.ALL)
//    private GraduateSchool graduateSchool;

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Address> getAddress() {
        return address;
    }

    public String toString() {
        String str = "id:" + id + "/name:" + name;
        Iterator<Address> it = address.iterator();
        while (it.hasNext()) {
            str += "/address:" + it.next().address;
        }
        return str;
    }


//    public GraduateSchool getGraduateSchool() {
//        return graduateSchool;
//    }
//
//    public void setGraduateSchool(GraduateSchool graduateSchool) {
//        this.graduateSchool = graduateSchool;
//    }
}