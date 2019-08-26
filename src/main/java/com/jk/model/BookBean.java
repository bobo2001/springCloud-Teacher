package com.jk.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Book")
public class BookBean {

    private Integer id;

    private  String name;

    private  String detail;

    private  String createTime;

    private  Integer price;

    private  Long    phone;



}
