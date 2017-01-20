package com.study.myassociation.entity;

import javax.persistence.*;

/**
 * Created by chenlian on 16/6/16.
 */
@Entity(name="E_GraduateSchool")
@Table(name="T_GraduateSchool")
public class GraduateSchool {
    @Id
    @GeneratedValue
    private Long id_gs;

    public String name;
}
