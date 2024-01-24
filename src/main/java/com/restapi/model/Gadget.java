package com.restapi.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "gadgets")
public class Gadget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;
    private Double price;


    private Integer count;

    private Long stock;


    private String photo;

//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "category_id", referencedColumnName = "id")
//    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "gadget")
    private List<Cart> carts = new ArrayList<>();

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
