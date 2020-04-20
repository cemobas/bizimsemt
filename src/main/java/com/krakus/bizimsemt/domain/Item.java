package com.krakus.bizimsemt.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Item {
    private String name;
    private String amount;
    private String unit;
}
