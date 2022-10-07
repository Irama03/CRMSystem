package com.WorkersS.models;

import com.WorkersS.utils.Utils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Contacts {

    @NotBlank
    @Max(25)
    @Column(name = "contacts_phone_number", length = 25)
    private String phoneNumber;

    @NotBlank
    @Max(50)
    @Column(name = "contacts_email", length = 50)
    private String email;

    public Contacts(@NotBlank @Max(25) String phoneNumber, @NotBlank @Max(50) String email) {
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contacts)) return false;
        Contacts contacts = (Contacts) o;
        return Utils.stringsAreEqual(getPhoneNumber(), contacts.getPhoneNumber()) &&
                Utils.stringsAreEqual(getEmail(), contacts.getEmail());
    }
}
