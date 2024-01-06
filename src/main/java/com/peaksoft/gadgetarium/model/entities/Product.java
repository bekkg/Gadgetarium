package com.peaksoft.gadgetarium.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.peaksoft.gadgetarium.model.enums.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int weight;
    private String color;
    private String screenSize;
    @Enumerated(EnumType.STRING)
    private Memory memory;
    private int simCard;
    private String processor;
    private String guarantee;
    private double discount;
    private LocalDate creatDate;
    private String rating;
    private double price;
    private String watchband;
    private String theMaterialOfTheCase;
    private String smartWatchSize;
    @Enumerated(EnumType.STRING)
    private SubCategory subCategory;
    @Enumerated(EnumType.STRING)
    private CaseShape caseShape;
    private boolean waterproof;
    @Enumerated(EnumType.STRING)
    private WirelessInterface w_i;
    @Enumerated(EnumType.STRING)
    private WaterResistance waterResistance;
    @Enumerated(value= EnumType.STRING)
    private OperationMemory operationMemory;
    @Enumerated(value=EnumType.STRING)
    WirelessInterface wirelessInterface;
    @Enumerated(value=EnumType.STRING)
    private OperationSystem operatingSystem;
    private String screen;
    private int inStock;
    private String image;
    private String video;
    private String pdf;
    private String description;
    private int totalPrice;
    private int quantity;
    private String brandName;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.PERSIST})
    @JoinColumn(name = "brands_id")
    private Brand brand;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "products_and_orders", joinColumns = @JoinColumn(name = "products_id"), inverseJoinColumns = @JoinColumn(name = "orders_id"))
    private List<Order> orders;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @JsonIgnore
    @ManyToMany(mappedBy = "products", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    List<User>users;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;
}
