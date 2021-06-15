package com.epam.spark.exam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Dekel Levitan
 */


@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "COUNTRY_OF_ORIGIN")
    private String CountryOfOrigin;
    @Column(name = "email")
    private String email;

    public User() {
    }


    public int getId() {
        return this.id;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getCountryOfOrigin() {
        return this.CountryOfOrigin;
    }

    public String getEmail() {
        return this.email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCountryOfOrigin(String CountryOfOrigin) {
        this.CountryOfOrigin = CountryOfOrigin;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof User)) return false;
        final User other = (User) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        final Object this$firstname = this.getFirstname();
        final Object other$firstname = other.getFirstname();
        if (this$firstname == null ? other$firstname != null : !this$firstname.equals(other$firstname)) return false;
        final Object this$lastName = this.getLastName();
        final Object other$lastName = other.getLastName();
        if (this$lastName == null ? other$lastName != null : !this$lastName.equals(other$lastName)) return false;
        final Object this$CountryOfOrigin = this.getCountryOfOrigin();
        final Object other$CountryOfOrigin = other.getCountryOfOrigin();
        if (this$CountryOfOrigin == null ? other$CountryOfOrigin != null : !this$CountryOfOrigin.equals(other$CountryOfOrigin))
            return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof User;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getId();
        final Object $firstname = this.getFirstname();
        result = result * PRIME + ($firstname == null ? 43 : $firstname.hashCode());
        final Object $lastName = this.getLastName();
        result = result * PRIME + ($lastName == null ? 43 : $lastName.hashCode());
        final Object $CountryOfOrigin = this.getCountryOfOrigin();
        result = result * PRIME + ($CountryOfOrigin == null ? 43 : $CountryOfOrigin.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        return result;
    }

    public String toString() {
        return "User(id=" + this.getId() + ", firstname=" + this.getFirstname() + ", lastName=" + this.getLastName() + ", CountryOfOrigin=" + this.getCountryOfOrigin() + ", email=" + this.getEmail() + ")";
    }
}
