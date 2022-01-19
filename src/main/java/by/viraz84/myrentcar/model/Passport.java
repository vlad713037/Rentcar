package by.viraz84.myrentcar.model;

import java.util.Objects;

public class Passport {
    private Integer id;
    private String passNumber;
    private String issuerRovd;
    private Integer usersId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassNumber() {
        return passNumber;
    }

    public void setPassNumber(String passNumber) {
        this.passNumber = passNumber;
    }

    public String getIssuerRovd() {
        return issuerRovd;
    }

    public void setIssuerRovd(String issuerRovd) {
        this.issuerRovd = issuerRovd;
    }

    public Integer getUsersId() {
        return usersId;
    }

    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passport passport = (Passport) o;
        return Objects.equals(id, passport.id) && Objects.equals(passNumber, passport.passNumber) &&
                Objects.equals(issuerRovd, passport.issuerRovd) && Objects.equals(usersId, passport.usersId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passNumber, issuerRovd, usersId);
    }

    @Override
    public String toString() {
        return "Passport{" +
                "id=" + id +
                ", passNumber='" + passNumber + '\'' +
                ", issuerRovd='" + issuerRovd + '\'' +
                ", usersId=" + usersId +
                '}';
    }
}


