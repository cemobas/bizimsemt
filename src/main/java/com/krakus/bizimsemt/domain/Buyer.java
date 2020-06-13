package com.krakus.bizimsemt.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document("buyers")
@TypeAlias("buyer")
public class Buyer {

    @Id
    private String id;
    @NotEmpty(message = "{buyer.name.not.empty}")
    private String name;
    @NotEmpty(message = "{buyer.surname.not.empty}")
    private String surname;

    @Pattern(regexp = "^[1-9]{1}[0-9]{9}[02468]{1}$", message = "{buyer.kimlikNo.not.empty}")
    private String kimlikNo;

    private Gender gender;

    @Past(message = "Tarih girin")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;

    @NotEmpty(message = "{buyer.address.not.empty}")
    private List<String> addresses;
}
