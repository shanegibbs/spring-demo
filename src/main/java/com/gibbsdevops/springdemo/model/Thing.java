package com.gibbsdevops.springdemo.model;

import javax.persistence.*;

@Entity
public class Thing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id;

    @Column(nullable = false, unique = true)
    private final String name;

    public Thing() {
        id = null;
        name = null;
    }

    public Thing(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
