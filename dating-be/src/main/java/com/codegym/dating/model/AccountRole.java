package com.codegym.dating.model;

import com.codegym.dating.model.composite.AccountRoleKey;
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
public class AccountRole {
    @EmbeddedId
    private AccountRoleKey id;
    @ManyToOne
    @MapsId("idAccount")
    @JoinColumn(name = "id_account")
    @JsonBackReference(value = "AccountRole_account")
    private Account account;
    @ManyToOne
    @MapsId("idRole")
    @JoinColumn(name = "id_role")
    @JsonBackReference(value = "AccountRole_role")
    private Role role;
}
