package com.study.myassociation.study_manytomany;

/**
 * Created by chenlian on 16/6/17.
 */


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Student")
public class Student implements Serializable {

    private Integer studentid;//学生ID
    private String studentName;//学生姓名


    private Set<Teacher> teachers = new HashSet<Teacher>();//对应的教师集合

    public Student() {
    }

    public Student(String studentName) {
        this.studentName = studentName;
    }

    @Id
    @GeneratedValue
    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    @Column(nullable = false, length = 32)
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /*
 * @ManyToMany 注释表示Student是多对多关系的一边，mappedBy 属性定义了Student 为双向关系的维护端
 */
    //说实话,这个mappedBy我真没有发现有什么用2016-6-18
    @ManyToMany(mappedBy = "students")
    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }
}

