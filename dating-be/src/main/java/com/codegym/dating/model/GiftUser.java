package com.codegym.dating.model;

import com.codegym.dating.model.composite.GiftUserKey;
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
public class GiftUser {
    @EmbeddedId
    private GiftUserKey id;
    @ManyToOne
    @MapsId("idGift")
    @JsonBackReference(value = "giftUser_gift")
    @JoinColumn(name = "id_gift")
    private Gift gift;
    @ManyToOne
    @MapsId("idSender")
    @JsonBackReference(value = "giftUser_sender")
    @JoinColumn(name = "id_user_sender")
    private User sender;
    @ManyToOne
    @MapsId("idReceiver")
    @JsonBackReference(value = "giftUser_receiver")
    @JoinColumn(name = "id_user_receiver")
    private User receiver;
    private Integer quantity;
}
