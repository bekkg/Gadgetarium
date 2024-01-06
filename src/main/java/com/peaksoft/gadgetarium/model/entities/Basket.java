package com.peaksoft.gadgetarium.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "baskets")
@Getter
@Setter
@NoArgsConstructor
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double total;
    private double discount;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "users_baskets", joinColumns = @JoinColumn(name = "users_id"), inverseJoinColumns = @JoinColumn(name = "baskets_id"))
    private List<Product> products;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "basket")
    private User user;

}
