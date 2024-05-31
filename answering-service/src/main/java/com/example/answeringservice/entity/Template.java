package com.example.answeringservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "template")
public class Template extends ParentEntity{

    @Column(name = "template_text")
    private String templateText;

    @OneToMany(mappedBy = "template", fetch = FetchType.LAZY)
    private List<Message> messages;
}
