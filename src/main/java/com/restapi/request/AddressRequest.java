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
    @NotBlank(message = "city is mandatory")
    private String address;

    @NotBlank(message = "city is mandatory")
    private String city;

    @Size(min = 2, max = 6)
    private Integer zipcode;
}
