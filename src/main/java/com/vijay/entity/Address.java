package com.vijay.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "address")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    /**
     *  address_id field
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;
    /**
     *  street file
     */
    @Column(name = "street")
    private String street;
    /**
     * city filed
     */
    @Column(name = "city")
    private String city;
    /**
     * state field
     */
    @Column(name = "state")
    private String state;
    /**
     * postal_code field
     */
    @Column(name = "postal_code")
    private String postalCode;

    /**
     * country field
     */
    @Column(name = "country")
    private String country;
    /**
     * create_at filed
     */
    @Column(name = "create_at")
    private LocalDateTime createAt;
    /**
     * updated_at
     */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    /**
     * deleted_at filed
     */
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;


}
