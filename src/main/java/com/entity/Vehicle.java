package com.entity;

import java.util.Objects;

public class Vehicle {
    private long id;
    private String mark;
    private String model;
    private String type;
    private int mileage;
    private int price;
    private long idProfile;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(long idProfile) {
        this.idProfile = idProfile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return id == vehicle.id && mileage == vehicle.mileage
                && price == vehicle.price && idProfile == vehicle.idProfile
                && Objects.equals(mark, vehicle.mark)
                && Objects.equals(model, vehicle.model)
                && Objects.equals(type, vehicle.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mark, model, type, mileage, price, idProfile);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", mileage=" + mileage +
                ", price=" + price +
                ", idProfile=" + idProfile +
                '}';
    }
}
