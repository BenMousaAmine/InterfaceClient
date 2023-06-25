package org.example;

public class Car {
    private int id;
    private String brand;
    private double cost;
    private double qty;


    public Car(int id, String brand, double cost, double qty) {
        setSeries(id);
        setBrand(brand);
        setValue(cost);
        setQty(qty);
    }

    public int getSeries() {
        return id;
    }

    public void setSeries(int id) {
        if(id > 0){
            this.id = id;
        }
    }

    public String getBrand() {
        return brand.toUpperCase();
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getCost() {
        return cost;
    }

    public void setValue(double cost) {
        if(cost > 0){
            this.cost = cost;
        }

    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty >= 0 ? qty : 0;
    }
    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + brand + '\'' +
                ", price=" + cost +
                ", quantity=" + qty +
                '}';
    }


}