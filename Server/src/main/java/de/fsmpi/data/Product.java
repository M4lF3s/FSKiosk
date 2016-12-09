package de.fsmpi.data;

import javax.persistence.*;

/**
 * Created by Marcel Fraas on 26.08.16.
 */


@Entity
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "Product")
    private String productName;

    @Column
    private double value;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public Product(String productName, double value) {
        this.productName = productName;
        this.value = value;
    }

    public Product() {}     // why JPA

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", value=" + value +
                '}';
    }
}
