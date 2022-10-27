package com.codegym.dating.model.composite;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class GiftUserKey implements Serializable {
    @Column(name = "id_gift")
    private Integer idGift;
    @Column(name = "id_user_sender")
    private Integer idSender;
    @Column(name = "id_user_receiver")
    private Integer idReceiver;
}
