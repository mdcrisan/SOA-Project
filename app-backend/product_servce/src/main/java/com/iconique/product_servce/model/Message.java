package com.iconique.product_servce.model;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Message {
    private String message;
    private String user;
    private String date;
 
}
