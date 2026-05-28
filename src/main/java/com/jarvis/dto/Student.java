package com.jarvis.dto;


import jakarta.persistence.*;

@Entity
@Table(name="Student_Jee_Rank")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentName;

    private int jeeRank;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getJeeRank() {
        return jeeRank;
    }

    public void setJeeRank(int jeeRank) {
        this.jeeRank = jeeRank;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", jeeRank=" + jeeRank +
                '}';
    }
}
