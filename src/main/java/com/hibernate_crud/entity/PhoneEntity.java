package com.hibernate_crud.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name = "customer_phone")
public class PhoneEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long phoneId;

    @Column(name = "phone")
    private String phone;


//    @ManyToOne
//    @JoinColumn (name = "id_customer")// Это место, где вы определяете связь
//    private CustomerEntity customer; // связь с CustomerEntity
}
