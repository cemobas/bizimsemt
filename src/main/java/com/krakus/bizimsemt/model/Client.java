package com.krakus.bizimsemt.model;

import com.krakus.bizimsemt.domain.Buyer;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public class Client extends RepresentationModel<Client> {

    private String id;
    private String name;
    private String surname;
    private List<String> addresses;

    public Client(String id, String name, String surname, List<String> addresses) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.addresses = addresses;
    }

    public static Client create(Buyer buyer) {
        return new Client(buyer.getId(), buyer.getName(), buyer.getSurname(), buyer.getAddresses());
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

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }
}
