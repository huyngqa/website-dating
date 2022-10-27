package com.codegym.dating.model;

import com.codegym.dating.model.composite.UserTargetKey;
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
public class UserTarget {
    @EmbeddedId
    private UserTargetKey id;
    @ManyToOne
    @JsonBackReference(value = "userTarget_user")
    @MapsId("idUser")
    @JoinColumn(name = "id_user")
    private User user;
    @ManyToOne
    @JsonBackReference(value = "userTarget_target")
    @MapsId("idTarget")
    @JoinColumn(name = "id_target")
    private Target target;
}
