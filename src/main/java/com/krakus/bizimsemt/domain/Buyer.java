package com.krakus.bizimsemt.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;


@Document("buyers")
@TypeAlias("buyer")
public class Buyer {

    @Id
    private String id;
    private String name;
    private String surname;
    private String kimlikNo;
    private LocalDateTime birthDate;
    private List<String> addresses;

    public Buyer(String id, String name, String surname, String kimlikNo, LocalDateTime birthDate, List<String> addresses) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.kimlikNo = kimlikNo;
        this.birthDate = birthDate;
        this.addresses = addresses;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getKimlikNo() {
        return kimlikNo;
    }

    public void setKimlikNo(String kimlikNo) {
        this.kimlikNo = kimlikNo;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }
}
