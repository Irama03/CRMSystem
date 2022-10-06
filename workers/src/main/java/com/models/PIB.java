package com.models;

import com.utils.Utils;
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
public class PIB {

    @NotBlank
    @Max(15)
    @Column(name = "person_name", length = 15)
    private String name;

    @Column(name = "person_lastname", length = 15)
    @Max(15)
    private String lastname;

    @NotBlank
    @Column(name = "person_surname", length = 15)
    @Max(15)
    private String surname;

    public PIB(@NotBlank @Max(15) String name, @Max(15) String lastname, @NotBlank @Max(15) String surname) {
        this.name = name;
        this.lastname = lastname;
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PIB)) return false;
        PIB pib = (PIB) o;
        return Utils.stringsAreEqual(getName(), pib.getName()) &&
                Utils.stringsAreEqual(getLastname(), pib.getLastname()) &&
                Utils.stringsAreEqual(getSurname(), pib.getSurname());
    }
}
