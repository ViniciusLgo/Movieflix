package com.movieflix.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Table(name = "streaming")
@NoArgsConstructor
@AllArgsConstructor
public class Streaming {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 100,nullable = false)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
