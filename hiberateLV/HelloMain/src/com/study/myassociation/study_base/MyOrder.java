package com.study.myassociation.study_base;





import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by chenlian on 16/6/17.
 */
@Entity
@Table

public class MyOrder {
    @Id
    @GeneratedValue
    Long gid;

    @Column(length = 255)
    private String backComment;

    @Transient
    private List<Goods> goodsId;

    public Season getHapperSeason() {
        return happerSeason;
    }

    public void setHapperSeason(Season happerSeason) {
        this.happerSeason = happerSeason;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public static enum Season
    {
        spring,summer,auttom,wintter
    }

    @Enumerated(EnumType.ORDINAL)
    private
    Season happerSeason;

    public String toString()
    {
        return "id:"+ getId() +"/dateTime:" + getDateTime();
    }

    @Temporal(TemporalType.DATE)
    private
    Date modifyTime;

    //还没有实验成功
    @Generated(GenerationTime.ALWAYS)
    private
    Date dateTimeCreatte;

    public Date getDateTime() {
        return dateTimeCreatte;
    }

    public void setDateTime(Date dateTime) {
        this.dateTimeCreatte = dateTime;
    }

    public Long getId() {
        return gid;
    }

    public void setId(Long id) {
        this.gid = id;
    }

    public List<Goods> getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(List<Goods> goodsId) {
        this.goodsId = goodsId;
    }

    public String getBackComment() {
        return backComment;
    }

    public void setBackComment(String backComment) {
        this.backComment = backComment;
    }
}
