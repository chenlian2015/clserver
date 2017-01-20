package com.study.myassociation.study_base;

import javax.persistence.*;
import java.util.*;

/**
 * Created by chenlian on 16/6/18.
 */
@Entity
@Table
public class Shop {
    @Id
    @GeneratedValue
    Long id;

    @ElementCollection(targetClass=String.class)
    @CollectionTable(name="Goods_inf", joinColumns = @JoinColumn(name="name", nullable = false))
    @Column(name="myGoods")
    @OrderColumn(name="list_order")
    private List<String> myGoods = new ArrayList<>();

    @ElementCollection(targetClass = Long.class)
    @CollectionTable(name="order_inf", joinColumns = @JoinColumn(name="ordername"))
    @Column(name="OrderId")
    private Set<Long> orders = new HashSet<>();

    @ElementCollection(targetClass = Float.class)
    @CollectionTable(name="score_inf", joinColumns = @JoinColumn(name="scoremap"))
    @MapKeyColumn(name="subject_name")
    @Column(name="column_score")
    @MapKeyClass(String.class)
    private Map<String, Float> scores = new HashMap<>();


    public List<String> getMyGoods() {
        return myGoods;
    }

    public void setMyGoods(List<String> myGoods) {
        this.myGoods = myGoods;
    }

    public String toString()
    {
        Iterator it = myGoods.iterator();
        String str = "id:"+id;
        while(it.hasNext())
        {
            str += "name"+ it.next();
        }
        return str;
    }

    public Set<Long> getOrders() {
        return orders;
    }

    public void setOrders(Set<Long> orders) {
        this.orders = orders;
    }

    public Map<String, Float> getScores() {
        return scores;
    }

    public void setScores(Map<String, Float> scores) {
        this.scores = scores;
    }
}
