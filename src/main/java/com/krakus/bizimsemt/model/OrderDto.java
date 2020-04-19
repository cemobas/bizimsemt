package com.krakus.bizimsemt.model;

import com.krakus.bizimsemt.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
public class OrderDto extends RepresentationModel<OrderDto> {

    @Id
    private String id;
    private String buyerName;
    private String sellerName;
    private List<Item> items;
    private Date orderDate;
    private Date lastModified;
}
