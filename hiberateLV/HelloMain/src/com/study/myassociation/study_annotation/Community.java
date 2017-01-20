package com.study.myassociation.study_annotation;

import com.study.myassociation.common.PrimaryKey;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by chenlian on 16/6/20.
 */
@Entity
@Table
public class Community {
    @Id
    @PrimaryKey
    Long id;

    public String toString()
    {
        return "Community id:"+id;
    }


}
