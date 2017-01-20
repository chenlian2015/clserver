package com.study.myassociation.study_manytomany;

/**
 * Created by chenlian on 16/6/17.
 */


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table
public class Teacher implements Serializable {

    private Integer teacherid;// 教师ID
    private String teacherName;// 教师姓名
    private Set<Student> students = new HashSet<Student>();// 对应的学生集合

    public Teacher() {

    }

    public Teacher(String teacherName) {
        this.teacherName = teacherName;
    }

    @Id
    @GeneratedValue
    public Integer getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(Integer teacherid) {
        this.teacherid = teacherid;
    }

    @Column(nullable = false, length = 32)
    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    /*
     * @ManyToMany 注释表示Teacher 是多对多关系的一端。
     * @JoinTable 描述了多对多关系的数据表关系，name属性指定中间表名称。
     * joinColumns 定义中间表与Teacher 表的外键关系，中间表Teacher_Student的Teacher_ID 列是Teacher 表的主键列对应的外键列。
     * inverseJoinColumns 属性定义了中间表与另外一端(Student)的外键关系。
     */
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "Teacher_Student",
            joinColumns = {@JoinColumn(name = "teacher_ID", referencedColumnName = "teacherid")},
            inverseJoinColumns = {@JoinColumn(name = "student_ID", referencedColumnName = "studentid")
            })
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        if (!this.students.contains(student)) {//检测在该散列表中某些键是否映射到指定值,value 查找的值。如果某些键映射到该散列表中的值为true，否则false
            this.students.add(student);
        }
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
    }

}
