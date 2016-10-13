package de.fsmpi.data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Marcel Fraas on 26.08.16.
 */


@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "Zeitstempel")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", Person=" + person.getPersonName() +
                ", Product=" + product.getProductName() +
                ", Wert=" + product.getValue() +
                '}';
    }


    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "Product", referencedColumnName = "id")
    private Product product;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "Person", referencedColumnName = "id")
    private Person person;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Transaction(Date timestamp, Product product, Person person) {
        this.timestamp = timestamp;
        this.product = product;
        this.person = person;
    }

    public Transaction() {}     // why JPA
}
