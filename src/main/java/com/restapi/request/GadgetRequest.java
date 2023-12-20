package com.restapi.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GadgetRequest {
    private Long id;
    private Long categoryId;
    private String title;
    private String description;
    private Double price;
  //  private byte[] photo;
}
