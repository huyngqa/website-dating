package com.codegym.dating.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;
    private String name;
    private LocalDate dateOfBirth;
    private Boolean gender;
    private String address;
    private String job;
    private Boolean married;

    @Column(columnDefinition = "TEXT")
    private String avatar;

    private LocalDate joinDay;
    private Integer coin;
    @ManyToOne
    @JoinColumn(name = "id_status_active")
    private StatusActive statusActive;
    @JsonBackReference(value = "user_account")
    @OneToOne(mappedBy = "user")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "id_type_user")
    private TypeUser typeUser;
    @OneToMany(mappedBy = "user")
    private List<UserHobbit> userHobbits;
    @OneToMany(mappedBy = "user")
    @JsonBackReference(value = "user_postList")
    private List<Post> postList;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference(value = "user_comments")
    private List<Comment> comments;
    @OneToMany(mappedBy = "user")
    @JsonBackReference(value = "user_invoice")
    private List<Invoice> invoices;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserTarget> userTargets;
    @OneToMany(mappedBy = "sender")
    private List<GiftUser> giftSenders;
    @OneToMany(mappedBy = "receiver")
    private List<GiftUser> giftReceiver;
}
