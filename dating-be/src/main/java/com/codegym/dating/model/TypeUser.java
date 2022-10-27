package com.codegym.dating.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class TypeUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTypeUser;
    private String typeUserName;
    @OneToMany(mappedBy = "typeUser")
    @JsonBackReference(value = "typeUser_users")
    private List<User> users;
}
