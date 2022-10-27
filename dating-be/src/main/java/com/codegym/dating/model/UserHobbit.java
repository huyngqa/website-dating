package com.codegym.dating.model;

import com.codegym.dating.model.composite.UserHobbitKey;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_has_hobbit")
public class UserHobbit {
    @EmbeddedId
    private UserHobbitKey id;
    @ManyToOne
    @MapsId("idHobbit")
    @JsonBackReference(value = "userHobbit_hobbit")
    @JoinColumn(name = "id_hobbit")
    private Hobbit hobbit;
    @ManyToOne
    @MapsId("idUser")
    @JsonBackReference(value = "userHobbit_user")
    @JoinColumn(name = "id_user")
    private User user;
}
