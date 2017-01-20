package com.study.myassociation.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenlian on 16/6/16.
 */
@Entity(name="E_ADDRESS")
@Table(name="T_ADDRESS")
public class Address {
    @Id
    @GeneratedValue
    private Long id;

    String address;

    @ManyToOne(cascade=CascadeType.ALL)
    //用于指定具体映射字段,否则默认为persons_id(表名_主键名)
    @JoinColumn(name="personid")
    public Person persons;


    public Address()
    {}
    public Address(String address)
    {
        this.address = address;
    }
}
