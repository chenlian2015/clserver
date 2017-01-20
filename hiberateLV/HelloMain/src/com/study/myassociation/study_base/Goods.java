package com.study.myassociation.study_base;

import org.hibernate.annotations.Formula;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by chenlian on 16/6/17.
 */
@Entity
@Table
public class Goods {
    @Id
    @GeneratedValue
    Long gid;

    private String name;

    private Double price;

    //还没有试验成功
    @Formula("(select concat(nt.name, nt.price) from Goods nt)")
    private String content;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public String toString()
    {
        return "id:"+gid+"/name:"+name+"/price:"+price+"/content:";
    }
}
