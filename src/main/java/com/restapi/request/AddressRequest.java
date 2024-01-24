package com.restapi.request;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressRequest {


    private Long id;
    private Long userId;
    private String address;


    private String city;


    private Integer pincode;

    private Integer phonenumber;
}
