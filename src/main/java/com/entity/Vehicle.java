package com.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Builder
@Data
public class Vehicle {
    private long id;
    private String mark;
    private String model;
    private String type;
    private int mileage;
    private int price;
    private long idProfile;
}
