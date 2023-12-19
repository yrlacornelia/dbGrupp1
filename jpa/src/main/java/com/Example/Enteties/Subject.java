package com.Example.Enteties;

import com.Example.JPAUtil;
import jakarta.persistence.*;

import java.util.function.Consumer;

@Entity
@Table(name = "subjects", schema = "Grupp1")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "subjectName", length = 120)
    private String subjectName;

    public Integer getId() {
        return id;
    }



    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

}

