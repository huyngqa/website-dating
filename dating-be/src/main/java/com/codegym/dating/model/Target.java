package com.codegym.dating.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Target {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTarget;
    private String targetName;
    @OneToMany(mappedBy = "target", cascade = CascadeType.ALL)
    private List<UserTarget> userTargets;
}
