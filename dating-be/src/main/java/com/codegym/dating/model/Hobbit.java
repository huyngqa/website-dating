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
public class Hobbit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHobbit;
    private String hobbitName;
    @OneToMany(mappedBy = "hobbit")
    private List<UserHobbit> userHobbits;
}
